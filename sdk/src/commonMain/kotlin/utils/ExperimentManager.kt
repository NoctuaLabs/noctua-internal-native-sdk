package com.noctuagames.labs.sdk.utils

internal object ExperimentManager {

    private const val KEY_CURRENT_EXPERIMENT = "current_experiment"
    private const val KEY_CURRENT_FEATURE = "current_feature"
    private const val KEY_CURRENT_SESSION_ID = "current_session_id"

    private val experimentFlags = mutableMapOf<String, Any?>()

    private fun <T> setFlag(key: String, value: T) {
        experimentFlags[key] = value
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getFlag(key: String, defaultValue: T): T {
        return experimentFlags[key] as? T ?: defaultValue
    }

    fun clear() {
        experimentFlags.clear()
    }

    fun setSessionId(sessionId: String) {
        setFlag(KEY_CURRENT_SESSION_ID, sessionId)
    }

    fun getSessionId(): String {
        return getFlag(KEY_CURRENT_SESSION_ID, "")
    }

    fun setGeneralExperiment(key: String, value: String) {
        setFlag(key, value)
    }

    fun getGeneralExperiment(key: String): String {
        return getFlag(key, "")
    }

    fun setExperiment(experimentName: String) {
        setFlag(KEY_CURRENT_EXPERIMENT, experimentName)
    }

    fun getExperiment(): String {
        return getFlag(KEY_CURRENT_EXPERIMENT, "")
    }

    fun setSessionTag(featureName: String) {
        setFlag(KEY_CURRENT_FEATURE, featureName)
    }

    fun getSessionTag(): String {
        return getFlag(KEY_CURRENT_FEATURE, "")
    }
}
