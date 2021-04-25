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
        var primitiveBoolData = fromJson<BoolData>(jsonString)
        assertEquals(true, primitiveBoolData.bool)
        assertEquals(true, primitiveBoolData.bool2)

        jsonString = """{"bool":true1,"bool2":true2}"""
        primitiveBoolData = fromJson(jsonString)
        assertEquals(false, primitiveBoolData.bool)
        assertEquals(false, primitiveBoolData.bool2)

        jsonString = """{"bool":null,"bool2":null}"""
        primitiveBoolData = fromJson(jsonString)
        assertEquals(false, primitiveBoolData.bool)
        assertEquals(false, primitiveBoolData.bool2)
    }

    @Test
    fun byteTest() {
        var jsonString = """{"byte1":null,"byte2":null}"""
        var data = fromJson<ByteData>(jsonString, ByteData::class.java)
        assertEquals(0.toByte(), data.byte1)
        assertEquals(0.toByte(), data.byte2)

        jsonString = """{"byte1":"null","byte2":"null"}"""
        data = fromJson(jsonString, ByteData::class.java)
        assertEquals(0.toByte(), data.byte1)
        assertEquals(0.toByte(), data.byte2)

        jsonString = """{"byte1":"","byte2":""}"""
        data = fromJson(jsonString, ByteData::class.java)
        assertEquals(0.toByte(), data.byte1)
        assertEquals(0.toByte(), data.byte2)

        jsonString = """{"byte1":"as","byte2":"df"}"""
        data = fromJson(jsonString, ByteData::class.java)
        assertEquals(0.toByte(), data.byte1)
        assertEquals(0.toByte(), data.byte2)

        jsonString = """{"byte1":"100","byte2":"100"}"""
        data = fromJson(jsonString, ByteData::class.java)
        assertEquals(100.toByte(), data.byte1)
        assertEquals(100.toByte(), data.byte2)

        jsonString = """{"byte1":55,"byte2":55}"""
        data = fromJson(jsonString, ByteData::class.java)
        assertEquals(55.toByte(), data.byte1)
        assertEquals(55.toByte(), data.byte2)

        jsonString = """{"byte1":128,"byte2":128}"""
        data = fromJson(jsonString, ByteData::class.java)
        assertEquals((-128).toByte(), data.byte1)
        assertEquals((-128).toByte(), data.byte2)
    }

    @Test
    fun shortTest() {
        var jsonString = """{"short1":null,"short2":null}"""
        var data = fromJson<ShortData>(jsonString, ShortData::class.java)
        assertEquals(0.toShort(), data.short1)
        assertEquals(0.toShort(), data.short2)

        jsonString = """{"short1":"null","short2":"null"}"""
        data = fromJson(jsonString, ShortData::class.java)
        assertEquals(0.toShort(), data.short1)
        assertEquals(0.toShort(), data.short2)

        jsonString = """{"short1":"","short2":""}"""
        data = fromJson(jsonString, ShortData::class.java)
        assertEquals(0.toShort(), data.short1)
        assertEquals(0.toShort(), data.short2)

        jsonString = """{"short1":"dfs","short2":"sdf"}"""
        data = fromJson(jsonString, ShortData::class.java)
        assertEquals(0.toShort(), data.short1)
        assertEquals(0.toShort(), data.short2)

        jsonString = """{"short1":"123","short2":"123"}"""
        data = fromJson(jsonString, ShortData::class.java)
        assertEquals(123.toShort(), data.short1)
        assertEquals(123.toShort(), data.short2)

        jsonString = """{"short1":123,"short2":123}"""
        data = fromJson(jsonString, ShortData::class.java)
        assertEquals(123.toShort(), data.short1)
        assertEquals(123.toShort(), data.short2)

        jsonString = """{"short1":32768,"short2":32768}"""
        data = fromJson(jsonString, ShortData::class.java)
        assertEquals((-32768).toShort(), data.short1)
        assertEquals((-32768).toShort(), data.short2)
    }

    val gson = newGson()

    private inline fun <reified T> fromJson(jsonString: String, type: Type = T::class.java): T {
        return gson.fromJson(jsonString, type)
    }

    private fun newGson() = Gson().newBuilder()
        .registerSafeTypeAdapters()
        .create()
}