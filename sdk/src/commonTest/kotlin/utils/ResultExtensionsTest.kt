package com.noctuagames.labs.sdk.utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ResultExtensionsTest {

    @Test
    fun onSuccess_executesAction_whenResultIsSuccess() {
        var executed = false
        val result: Result<String, DataError.Remote> = Result.Success("data")

        result.onSuccess { executed = true }

        assertTrue(executed)
    }

    @Test
    fun onSuccess_doesNotExecuteAction_whenResultIsError() {
        var executed = false
        val result: Result<String, DataError.Remote> = Result.Error(DataError.Remote.UNKNOWN)

        result.onSuccess { executed = true }

        assertFalse(executed)
    }

    @Test
    fun onError_executesAction_whenResultIsError() {
        var capturedError: DataError.Remote? = null
        val result: Result<String, DataError.Remote> = Result.Error(DataError.Remote.NO_INTERNET)

        result.onError { capturedError = it }

        assertEquals(DataError.Remote.NO_INTERNET, capturedError)
    }

    @Test
    fun onError_doesNotExecuteAction_whenResultIsSuccess() {
        var executed = false
        val result: Result<String, DataError.Remote> = Result.Success("data")

        result.onError { executed = true }

        assertFalse(executed)
    }

    @Test
    fun map_transformsData_whenResultIsSuccess() {
        val result: Result<Int, DataError.Remote> = Result.Success(42)

        val mapped = result.map { it.toString() }

        assertIs<Result.Success<String>>(mapped)
        assertEquals("42", mapped.data)
    }

    @Test
    fun map_passesThroughError_whenResultIsError() {
        val result: Result<Int, DataError.Remote> = Result.Error(DataError.Remote.SERVER)

        val mapped = result.map { it.toString() }

        assertIs<Result.Error<DataError.Remote>>(mapped)
        assertEquals(DataError.Remote.SERVER, mapped.error)
    }

    @Test
    fun asEmptyDataResult_convertsSuccessToUnit() {
        val result: Result<String, DataError.Remote> = Result.Success("data")

        val empty = result.asEmptyDataResult()

        assertIs<Result.Success<Unit>>(empty)
        assertEquals(Unit, empty.data)
    }
}
