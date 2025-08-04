package gg.noctua.internal.utils

import android.content.Context
import gg.noctua.internal.data.models.NoctuaConfig
import kotlinx.io.IOException
import kotlinx.serialization.json.Json
import java.lang.ref.WeakReference

actual object AppContext {
    private var value: WeakReference<Context?>? = null

    fun set(context: Context) {
        value = WeakReference(context)
    }
    internal fun get(): Context {
        return value?.get() ?: throw RuntimeException("Context Error")
    }
}

actual fun loadAppConfig(): NoctuaConfig {
    try {
        AppContext.get().assets.open("noctuagg.json").use {
            val json = it.bufferedReader().use { reader -> reader.readText() }
            return Json.decodeFromString<NoctuaConfig>(json)
        }
    } catch (e: IOException) {
        throw IllegalArgumentException("Failed to load noctuagg.json", e)
    }
}