package me.twc.gson.adapter

import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import java.lang.reflect.Type

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    data class BooleanData(
        val bool: Boolean = false
    )

    @Test
    fun booleanText() {
        var jsonString = """{"bool":null}"""
        var booleanData = fromJson<BooleanData>(jsonString)
        assertEquals(false, booleanData.bool)

        jsonString = """{"bool":"null"}"""
        booleanData = fromJson(jsonString)
        assertEquals(false, booleanData.bool)

        jsonString = """{"bool":""}"""
        booleanData = fromJson(jsonString)
        assertEquals(false, booleanData.bool)

        jsonString = """{"bool":"abc"}"""
        booleanData = fromJson(jsonString)
        assertEquals(false, booleanData.bool)

        jsonString = """{"bool":"false"}"""
        booleanData = fromJson(jsonString)
        assertEquals(false, booleanData.bool)

        jsonString = """{"bool":false}"""
        booleanData = fromJson(jsonString)
        assertEquals(false, booleanData.bool)

        jsonString = """{"bool":False}"""
        booleanData = fromJson(jsonString)
        assertEquals(false, booleanData.bool)

        jsonString = """{"bool":FALSE}"""
        booleanData = fromJson(jsonString)
        assertEquals(false, booleanData.bool)

        jsonString = """{"bool":"true"}"""
        booleanData = fromJson(jsonString)
        assertEquals(true, booleanData.bool)

        jsonString = """{"bool":true}"""
        booleanData = fromJson(jsonString)
        assertEquals(true, booleanData.bool)

        jsonString = """{"bool":True}"""
        booleanData = fromJson(jsonString)
        assertEquals(true, booleanData.bool)

        jsonString = """{"bool":TRUE}"""
        booleanData = fromJson(jsonString)
        assertEquals(true, booleanData.bool)

        jsonString = """{"bool":true,"bool2":true}"""
        var primitiveBoolData = fromJson<PrimitiveBoolData>(jsonString)
        assertEquals(true, primitiveBoolData.bool)
        assertEquals(true, primitiveBoolData.bool2)

        jsonString = """{"bool":true1,"bool2":true2}"""
        primitiveBoolData = fromJson(jsonString)
        assertEquals(false, primitiveBoolData.bool)
        assertEquals(false, primitiveBoolData.bool2)
    }


    val gson = newGson()

    private inline fun <reified T> fromJson(jsonString: String, type: Type = T::class.java): T {
        return gson.fromJson(jsonString, type)
    }

    private fun newGson() = Gson().newBuilder()
        .registerSafeTypeAdapters()
        .create()
}