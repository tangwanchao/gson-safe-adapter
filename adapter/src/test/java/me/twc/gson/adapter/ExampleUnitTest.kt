package me.twc.gson.adapter

import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import java.lang.StringBuilder
import java.lang.reflect.Type
import java.text.DateFormat
import java.util.*

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

    @Test
    fun integerTest() {
        var jsonString = """{"int1":null,"int2":null}"""
        var data = fromJson<IntData>(jsonString, IntData::class.java)
        assertEquals(0, data.int1)
        assertEquals(0, data.int2)

        jsonString = """{"int1":"null","int2":"null"}"""
        data = fromJson(jsonString, IntData::class.java)
        assertEquals(0, data.int1)
        assertEquals(0, data.int2)

        jsonString = """{"int1":"","int2":""}"""
        data = fromJson(jsonString, IntData::class.java)
        assertEquals(0, data.int1)
        assertEquals(0, data.int2)

        jsonString = """{"int1":"abc","int2":"abc"}"""
        data = fromJson(jsonString, IntData::class.java)
        assertEquals(0, data.int1)
        assertEquals(0, data.int2)

        jsonString = """{"int1":"123","int2":"123"}"""
        data = fromJson(jsonString, IntData::class.java)
        assertEquals(123, data.int1)
        assertEquals(123, data.int2)

        jsonString = """{"int1":123,"int2":123}"""
        data = fromJson(jsonString, IntData::class.java)
        assertEquals(123, data.int1)
        assertEquals(123, data.int2)

        jsonString = """{"int1":2147483648,"int2":2147483648}"""
        data = fromJson(jsonString, IntData::class.java)
        assertEquals(0, data.int1)
        assertEquals(0, data.int2)
    }

    @Test
    fun longTest() {
        var jsonString = """{"long1":null,"long2":null}"""
        var data = fromJson<LongData>(jsonString, LongData::class.java)
        assertEquals(0L, data.long1)
        assertEquals(0L, data.long2)

        jsonString = """{"long1":"null","long2":"null"}"""
        data = fromJson(jsonString, LongData::class.java)
        assertEquals(0L, data.long1)
        assertEquals(0L, data.long2)

        jsonString = """{"long1":"","long2":""}"""
        data = fromJson(jsonString, LongData::class.java)
        assertEquals(0L, data.long1)
        assertEquals(0L, data.long2)

        jsonString = """{"long1":"abc","long2":"sdf"}"""
        data = fromJson(jsonString, LongData::class.java)
        assertEquals(0L, data.long1)
        assertEquals(0L, data.long2)

        jsonString = """{"long1":"123","long2":"123"}"""
        data = fromJson(jsonString, LongData::class.java)
        assertEquals(123L, data.long1)
        assertEquals(123L, data.long2)

        jsonString = """{"long1":123,"long2":123}"""
        data = fromJson(jsonString, LongData::class.java)
        assertEquals(123L, data.long1)
        assertEquals(123L, data.long2)

        jsonString = """{"long1":19223372036854775890,"long2":19223372036854775890}"""
        data = fromJson(jsonString, LongData::class.java)
        assertEquals(0L, data.long1)
        assertEquals(0L, data.long2)
    }

    @Test
    fun floatTest() {
        var jsonString = """{"float1":null,"float2":null}"""
        var data = fromJson<FloatData>(jsonString, FloatData::class.java)
        assertEquals(0f, data.float1)
        assertEquals(0f, data.float2)

        jsonString = """{"float1":"null","float2":"null"}"""
        data = fromJson(jsonString, FloatData::class.java)
        assertEquals(0f, data.float1)
        assertEquals(0f, data.float2)

        jsonString = """{"float1":"","float2":""}"""
        data = fromJson(jsonString, FloatData::class.java)
        assertEquals(0f, data.float1)
        assertEquals(0f, data.float2)

        jsonString = """{"float1":"abc","float2":"abc"}"""
        data = fromJson(jsonString, FloatData::class.java)
        assertEquals(0f, data.float1)
        assertEquals(0f, data.float2)

        jsonString = """{"float1":"123.123","float2":"123.123"}"""
        data = fromJson(jsonString, FloatData::class.java)
        assertEquals(123.123f, data.float1)
        assertEquals(123.123f, data.float2)

        jsonString = """{"float1":123.123,"float2":123.123}"""
        data = fromJson(jsonString, FloatData::class.java)
        assertEquals(123.123f, data.float1)
        assertEquals(123.123f, data.float2)

        jsonString =
            """{"float1":192233721922337203685477589019223372036854775890036854775890.333,"float2":191921922337203685477589023372036854775890223372036854775890.333}"""
        data = fromJson(jsonString, FloatData::class.java)
        assertEquals(Float.POSITIVE_INFINITY, data.float1)
        assertEquals(Float.POSITIVE_INFINITY, data.float2)
    }

    @Test
    fun doubleTest() {
        var jsonString = """{"double1":null,"double2":null}"""
        var data = fromJson<DoubleData>(jsonString, DoubleData::class.java)
        assertEquals(0.0, data.double1, 0.0)
        assertEquals(0.0, data.double2, 0.0)

        jsonString = """{"double1":"null","double2":"null"}"""
        data = fromJson(jsonString, DoubleData::class.java)
        assertEquals(0.0, data.double1, 0.0)
        assertEquals(0.0, data.double2, 0.0)

        jsonString = """{"double1":"","double2":""}"""
        data = fromJson(jsonString, DoubleData::class.java)
        assertEquals(0.0, data.double1, 0.0)
        assertEquals(0.0, data.double2, 0.0)

        jsonString = """{"double1":"abc","double2":"def"}"""
        data = fromJson(jsonString, DoubleData::class.java)
        assertEquals(0.0, data.double1, 0.0)
        assertEquals(0.0, data.double2, 0.0)

        jsonString = """{"double1":"123.321","double2":"123.321"}"""
        data = fromJson(jsonString, DoubleData::class.java)
        assertEquals(123.321, data.double1, 0.0)
        assertEquals(123.321, data.double2, 0.0)

        jsonString = """{"double1":123.321,"double2":123.321}"""
        data = fromJson(jsonString, DoubleData::class.java)
        assertEquals(123.321, data.double1, 0.0)
        assertEquals(123.321, data.double2, 0.0)

        jsonString = """{
            |"double1":192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890.333,
            |"double2":192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890192233721922337203685477589019223372036854775890036854775890.333}""".trimMargin()
        data = fromJson(jsonString, DoubleData::class.java)
        assertEquals(Double.POSITIVE_INFINITY, data.double1, 0.0)
        assertEquals(Double.POSITIVE_INFINITY, data.double2, 0.0)
    }

    @Test
    fun stringTest() {
        var jsonString = """{"string1":null,"string2":null}"""
        var data = fromJson<StringData>(jsonString, StringData::class.java)
        assertEquals("", data.string1)
        assertEquals("", data.string2)

        jsonString = """{"string1":"null","string2":"null"}"""
        data = fromJson(jsonString, StringData::class.java)
        assertEquals("", data.string1)
        assertEquals("", data.string2)

        jsonString = """{"string1":"Null","string2":"nUll"}"""
        data = fromJson(jsonString, StringData::class.java)
        assertEquals("", data.string1)
        assertEquals("", data.string2)

        jsonString = """{"string1":"","string2":""}"""
        data = fromJson(jsonString, StringData::class.java)
        assertEquals("", data.string1)
        assertEquals("", data.string2)

        jsonString = """{"string1":"123","string2":123}"""
        data = fromJson(jsonString, StringData::class.java)
        assertEquals("123", data.string1)
        assertEquals("123", data.string2)

        jsonString = """{"string1":"abc","string2":"abc"}"""
        data = fromJson(jsonString, StringData::class.java)
        assertEquals("abc", data.string1)
        assertEquals("abc", data.string2)

        val outData = StringData()
        outData.string1 = "null"
        outData.string2 = "null"
        val outJsonString = gson.toJson(outData)
        assertEquals("""{"string1":"","string2":""}""", outJsonString)
    }

    @Test
    fun stringIncludeNullStringTest() {
        var jsonString = """{"string1":null,"string2":null}"""
        var data = fromJson<StringData>(jsonString, StringData::class.java, true)
        assertEquals("", data.string1)
        assertEquals("", data.string2)

        jsonString = """{"string1":"null","string2":"null"}"""
        data = fromJson(jsonString, StringData::class.java, true)
        assertEquals("null", data.string1)
        assertEquals("null", data.string2)

        jsonString = """{"string1":"Null","string2":"nUll"}"""
        data = fromJson(jsonString, StringData::class.java, true)
        assertEquals("Null", data.string1)
        assertEquals("nUll", data.string2)

        jsonString = """{"string1":"","string2":""}"""
        data = fromJson(jsonString, StringData::class.java, true)
        assertEquals("", data.string1)
        assertEquals("", data.string2)

        jsonString = """{"string1":"123","string2":123}"""
        data = fromJson(jsonString, StringData::class.java, true)
        assertEquals("123", data.string1)
        assertEquals("123", data.string2)

        jsonString = """{"string1":"abc","string2":"abc"}"""
        data = fromJson(jsonString, StringData::class.java, true)
        assertEquals("abc", data.string1)
        assertEquals("abc", data.string2)

        val outData = StringData()
        outData.string1 = "null"
        outData.string2 = "null"
        val outJsonString = gsonIncludeNullString.toJson(outData)
        assertEquals("""{"string1":"null","string2":"null"}""", outJsonString)
    }

    @Test
    fun stringBuilder() {
        var jsonString = """{"builder1":null,"builder2":null}"""
        var data = fromJson<StringBuilderData>(jsonString, StringBuilderData::class.java, false)
        assertEquals("", data.builder1.toString())
        assertEquals("", data.builder2.toString())

        jsonString = """{"builder1":"null","builder2":"null"}"""
        data = fromJson(jsonString, StringBuilderData::class.java, false)
        assertEquals("", data.builder1.toString())
        assertEquals("", data.builder2.toString())

        jsonString = """{"builder1":"Null","builder2":"nUll"}"""
        data = fromJson(jsonString, StringBuilderData::class.java, false)
        assertEquals("", data.builder1.toString())
        assertEquals("", data.builder2.toString())

        jsonString = """{"builder1":"","builder2":""}"""
        data = fromJson(jsonString, StringBuilderData::class.java, false)
        assertEquals("", data.builder1.toString())
        assertEquals("", data.builder2.toString())

        jsonString = """{"builder1":"123","builder2":123}"""
        data = fromJson(jsonString, StringBuilderData::class.java, false)
        assertEquals("123", data.builder1.toString())
        assertEquals("123", data.builder2.toString())

        jsonString = """{"builder1":"abc","builder2":"abc"}"""
        data = fromJson(jsonString, StringBuilderData::class.java, false)
        assertEquals("abc", data.builder1.toString())
        assertEquals("abc", data.builder2.toString())

        val outData = StringBuilderData()
        outData.builder1 = StringBuilder("null")
        outData.builder2 = StringBuilder("null")
        val outJsonString = gson.toJson(outData)
        assertEquals("""{"builder1":"","builder2":""}""", outJsonString)
    }

    @Test
    fun stringBuilderIncludeNullString() {
        var jsonString = """{"builder1":null,"builder2":null}"""
        var data = fromJson<StringBuilderData>(jsonString, StringBuilderData::class.java, true)
        assertEquals("", data.builder1.toString())
        assertEquals("", data.builder2.toString())

        jsonString = """{"builder1":"null","builder2":"null"}"""
        data = fromJson(jsonString, StringBuilderData::class.java, true)
        assertEquals("null", data.builder1.toString())
        assertEquals("null", data.builder2.toString())

        jsonString = """{"builder1":"Null","builder2":"nUll"}"""
        data = fromJson(jsonString, StringBuilderData::class.java, true)
        assertEquals("Null", data.builder1.toString())
        assertEquals("nUll", data.builder2.toString())

        jsonString = """{"builder1":"","builder2":""}"""
        data = fromJson(jsonString, StringBuilderData::class.java, true)
        assertEquals("", data.builder1.toString())
        assertEquals("", data.builder2.toString())

        jsonString = """{"builder1":"123","builder2":123}"""
        data = fromJson(jsonString, StringBuilderData::class.java, true)
        assertEquals("123", data.builder1.toString())
        assertEquals("123", data.builder2.toString())

        jsonString = """{"builder1":"abc","builder2":"abc"}"""
        data = fromJson(jsonString, StringBuilderData::class.java, true)
        assertEquals("abc", data.builder1.toString())
        assertEquals("abc", data.builder2.toString())

        val outData = StringBuilderData()
        outData.builder1 = StringBuilder("null")
        outData.builder2 = StringBuilder("null")
        val outJsonString = gsonIncludeNullString.toJson(outData)
        assertEquals("""{"builder1":"null","builder2":"null"}""", outJsonString)
    }

    @Test
    fun stringBuffer() {
        var jsonString = """{"buffer1":null,"buffer2":null}"""
        var data = fromJson<StringBufferData>(jsonString, StringBufferData::class.java, false)
        assertEquals("", data.buffer1.toString())
        assertEquals("", data.buffer2.toString())

        jsonString = """{"buffer1":"null","buffer2":"null"}"""
        data = fromJson(jsonString, StringBufferData::class.java, false)
        assertEquals("", data.buffer1.toString())
        assertEquals("", data.buffer2.toString())

        jsonString = """{"buffer1":"Null","buffer2":"nUll"}"""
        data = fromJson(jsonString, StringBufferData::class.java, false)
        assertEquals("", data.buffer1.toString())
        assertEquals("", data.buffer2.toString())

        jsonString = """{"buffer1":"","buffer2":""}"""
        data = fromJson(jsonString, StringBufferData::class.java, false)
        assertEquals("", data.buffer1.toString())
        assertEquals("", data.buffer2.toString())

        jsonString = """{"buffer1":"123","buffer2":123}"""
        data = fromJson(jsonString, StringBufferData::class.java, false)
        assertEquals("123", data.buffer1.toString())
        assertEquals("123", data.buffer2.toString())

        jsonString = """{"buffer1":"abc","buffer2":"abc"}"""
        data = fromJson(jsonString, StringBufferData::class.java, false)
        assertEquals("abc", data.buffer1.toString())
        assertEquals("abc", data.buffer2.toString())

        val outData = StringBufferData()
        outData.buffer1 = StringBuffer("null")
        outData.buffer2 = StringBuffer("null")
        val outJsonString = gson.toJson(outData)
        assertEquals("""{"buffer1":"","buffer2":""}""", outJsonString)
    }

    @Test
    fun stringBufferIncludeNullString() {
        var jsonString = """{"buffer1":null,"buffer2":null}"""
        var data = fromJson<StringBufferData>(jsonString, StringBufferData::class.java, true)
        assertEquals("", data.buffer1.toString())
        assertEquals("", data.buffer2.toString())

        jsonString = """{"buffer1":"null","buffer2":"null"}"""
        data = fromJson(jsonString, StringBufferData::class.java, true)
        assertEquals("null", data.buffer1.toString())
        assertEquals("null", data.buffer2.toString())

        jsonString = """{"buffer1":"Null","buffer2":"nUll"}"""
        data = fromJson(jsonString, StringBufferData::class.java, true)
        assertEquals("Null", data.buffer1.toString())
        assertEquals("nUll", data.buffer2.toString())

        jsonString = """{"buffer1":"","buffer2":""}"""
        data = fromJson(jsonString, StringBufferData::class.java, true)
        assertEquals("", data.buffer1.toString())
        assertEquals("", data.buffer2.toString())

        jsonString = """{"buffer1":"123","buffer2":123}"""
        data = fromJson(jsonString, StringBufferData::class.java, true)
        assertEquals("123", data.buffer1.toString())
        assertEquals("123", data.buffer2.toString())

        jsonString = """{"buffer1":"abc","buffer2":"abc"}"""
        data = fromJson(jsonString, StringBufferData::class.java, true)
        assertEquals("abc", data.buffer1.toString())
        assertEquals("abc", data.buffer2.toString())

        val outData = StringBufferData()
        outData.buffer1 = StringBuffer("null")
        outData.buffer2 = StringBuffer("null")
        val outJsonString = gsonIncludeNullString.toJson(outData)
        assertEquals("""{"buffer1":"null","buffer2":"null"}""", outJsonString)
    }

    @Test
    fun bigIntegerTest() {
        var jsonString = """{"big1":null,"big2":null}"""
        var data = fromJson<BigIntegerData>(jsonString, BigIntegerData::class.java)
        assertEquals(0, data.big1.toInt())
        assertEquals(0, data.big2.toInt())

        jsonString = """{"big1":"null","big2":"null"}"""
        data = fromJson(jsonString, BigIntegerData::class.java)
        assertEquals(0, data.big1.toInt())
        assertEquals(0, data.big2.toInt())

        jsonString = """{"big1":"","big2":""}"""
        data = fromJson(jsonString, BigIntegerData::class.java)
        assertEquals(0, data.big1.toInt())
        assertEquals(0, data.big2.toInt())

        jsonString = """{"big1":"abc","big2":"abc"}"""
        data = fromJson(jsonString, BigIntegerData::class.java)
        assertEquals(0, data.big1.toInt())
        assertEquals(0, data.big2.toInt())

        jsonString = """{"big1":"12345","big2":"12345"}"""
        data = fromJson(jsonString, BigIntegerData::class.java)
        assertEquals(12345, data.big1.toInt())
        assertEquals(12345, data.big2.toInt())

        jsonString = """{"big1":12345,"big2":12345}"""
        data = fromJson(jsonString, BigIntegerData::class.java)
        assertEquals(12345, data.big1.toInt())
        assertEquals(12345, data.big2.toInt())

        jsonString =
            """{"big1":123456789123456789123456789123456789,"big2":123456789123456789123456789123456789}"""
        data = fromJson(jsonString, BigIntegerData::class.java)
        assertEquals("123456789123456789123456789123456789", data.big1.toString())
        assertEquals("123456789123456789123456789123456789", data.big2.toString())

        val outString = gson.toJson(data)
        assertEquals(
            """{"big1":"123456789123456789123456789123456789","big2":"123456789123456789123456789123456789"}""",
            outString
        )
    }

    @Test
    fun bigDecimalTest() {
        var jsonString = """{"big1":null,"big2":null}"""
        var data = fromJson<BigDecimalData>(jsonString, BigDecimalData::class.java)
        assertEquals("0.0", data.big1.toPlainString())
        assertEquals("0.0", data.big2.toPlainString())

        jsonString = """{"big1":"null","big2":"null"}"""
        data = fromJson(jsonString, BigDecimalData::class.java)
        assertEquals("0.0", data.big1.toPlainString())
        assertEquals("0.0", data.big2.toPlainString())

        jsonString = """{"big1":"","big2":""}"""
        data = fromJson(jsonString, BigDecimalData::class.java)
        assertEquals("0.0", data.big1.toPlainString())
        assertEquals("0.0", data.big2.toPlainString())

        jsonString = """{"big1":"abc","big2":"abc"}"""
        data = fromJson(jsonString, BigDecimalData::class.java)
        assertEquals("0.0", data.big1.toPlainString())
        assertEquals("0.0", data.big2.toPlainString())

        jsonString = """{"big1":"123.456","big2":"123.456"}"""
        data = fromJson(jsonString, BigDecimalData::class.java)
        assertEquals("123.456", data.big1.toPlainString())
        assertEquals("123.456", data.big2.toPlainString())

        jsonString = """{"big1":"123.4560","big2":"123.4560"}"""
        data = fromJson(jsonString, BigDecimalData::class.java)
        assertEquals("123.4560", data.big1.toPlainString())
        assertEquals("123.4560", data.big2.toPlainString())

        jsonString = """{"big1":123.456,"big2":123.456}"""
        data = fromJson(jsonString, BigDecimalData::class.java)
        assertEquals("123.456", data.big1.toPlainString())
        assertEquals("123.456", data.big2.toPlainString())

        jsonString = """{"big1":123.4560,"big2":123.4560}"""
        data = fromJson(jsonString, BigDecimalData::class.java)
        assertEquals("123.4560", data.big1.toPlainString())
        assertEquals("123.4560", data.big2.toPlainString())

        jsonString =
            """{"big1":123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789,"big2":123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789}"""
        data = fromJson(jsonString, BigDecimalData::class.java)
        assertEquals(
            "123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789",
            data.big1.toPlainString()
        )
        assertEquals(
            "123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789",
            data.big2.toPlainString()
        )

        val outString = gson.toJson(data)
        assertEquals(
            """{"big1":"123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789","big2":"123456789123456789123456789123456789123456789123456789.123456789123456789123456789123456789123456789123456789"}""",
            outString
        )
    }

    @Test
    fun collectionTest() {
        var jsonString = """{"arr1":null,"arr2":null}"""
        var data = fromJson<CollectionData>(jsonString, CollectionData::class.java)
        assertEquals(0, data.arr1.size)
        assertEquals(0, data.arr2.size)

        jsonString = """{"arr1":"null","arr2":"null"}"""
        data = fromJson(jsonString, CollectionData::class.java)
        assertEquals(0, data.arr1.size)
        assertEquals(0, data.arr2.size)

        jsonString = """{"arr1":"","arr2":""}"""
        data = fromJson(jsonString, CollectionData::class.java)
        assertEquals(0, data.arr1.size)
        assertEquals(0, data.arr2.size)

        jsonString = """{"arr1":[],"arr2":[]}"""
        data = fromJson(jsonString, CollectionData::class.java)
        assertEquals(0, data.arr1.size)
        assertEquals(0, data.arr2.size)

        jsonString = """{"arr1":["a","b"],"arr2":["c","d"]}"""
        data = fromJson(jsonString, CollectionData::class.java)
        assertEquals(2, data.arr1.size)
        assertEquals(2, data.arr2.size)

        val outData = CollectionData()
        val outJsonString = gson.toJson(outData)
        assertEquals("""{"arr1":[],"arr2":[]}""", outJsonString)
    }

    @Test
    fun arrayTest() {
        var jsonString = """{"arr1":null,"arr2":null}"""
        var data = fromJson<ArrayData>(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":"null","arr2":"null"}"""
        data = fromJson(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":"","arr2":""}"""
        data = fromJson(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":"abc","arr2":"abc"}"""
        data = fromJson(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":[],"arr2":[]}"""
        data = fromJson(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":["a","b"],"arr2":["c","d"]}"""
        data = fromJson(jsonString)
        assertEquals(2,data.arr1.size)
        assertEquals(2,data.arr2.size)
    }

    @Test
    fun kotlinArrayTest() {
        var jsonString = """{"arr1":null,"arr2":null}"""
        var data = fromJson<KotlinArrayData>(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":"null","arr2":"null"}"""
        data = fromJson(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":"","arr2":""}"""
        data = fromJson(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":"abc","arr2":"abc"}"""
        data = fromJson(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":[],"arr2":[]}"""
        data = fromJson(jsonString)
        assertEquals(0,data.arr1.size)
        assertEquals(0,data.arr2.size)

        jsonString = """{"arr1":["a","b"],"arr2":["c","d"]}"""
        data = fromJson(jsonString)
        assertEquals(2,data.arr1.size)
        assertEquals(2,data.arr2.size)
    }

    @Test
    fun dateTest() {
        val formatter = DateFormat.getDateTimeInstance(
            DateFormat.MEDIUM,
            DateFormat.MEDIUM,
            Locale.CHINA
        )

        var jsonString = """{"date1":null,"date2":null}"""
        var data = fromJson<DateData>(jsonString)
        assertTrue(System.currentTimeMillis() - data.date1.time < 1000L)
        assertTrue(System.currentTimeMillis() - data.date2.time < 1000L)

        jsonString = """{"date1":"null","date2":"null"}"""
        data = fromJson(jsonString)
        assertTrue(System.currentTimeMillis() - data.date1.time < 1000L)
        assertTrue(System.currentTimeMillis() - data.date2.time < 1000L)

        jsonString = """{"date1":"","date2":""}"""
        data = fromJson(jsonString)
        assertTrue(System.currentTimeMillis() - data.date1.time < 1000L)
        assertTrue(System.currentTimeMillis() - data.date2.time < 1000L)

        jsonString = """{"date1":"ads","date2":"fdsa"}"""
        data = fromJson(jsonString)
        assertTrue(System.currentTimeMillis() - data.date1.time < 1000L)
        assertTrue(System.currentTimeMillis() - data.date2.time < 1000L)

        jsonString = """{"date1":"2021-4-26 19:35:09","date2":"2021-4-26 19:35:09"}"""
        data = fromJson(jsonString)
        assertEquals(formatter.parse("2021-4-26 19:35:09"),data.date1)
        assertEquals(formatter.parse("2021-4-26 19:35:09"),data.date2)

        var outJsonString = gson.toJson(data)
        assertEquals("""{"date1":"2021-4-26 19:35:09","date2":"2021-4-26 19:35:09"}""",outJsonString)

        val outData = DateData()
        outData.date1 = Date()
        outData.date2 = Date()
        outJsonString = gson.toJson(outData)
        data = fromJson(outJsonString)
        val currentTimeMillis = System.currentTimeMillis()
        assertTrue(currentTimeMillis - data.date1.time < 1000L)
        assertTrue(currentTimeMillis - data.date2.time < 1000L)
    }

    val gson = newGson()
    val gsonIncludeNullString = newGson(true)

    private inline fun <reified T> fromJson(
        jsonString: String,
        type: Type = T::class.java,
        includeNullString: Boolean = false
    ): T {
        val g = if (includeNullString) gsonIncludeNullString else gson
        return g.fromJson(jsonString, type)
    }

    private fun newGson(includeNullString: Boolean = false): Gson {
        val stringFactory = if (includeNullString) {
            GsonTypeAdapters.STRING_INCLUDE_NULL_STRING_FACTORY
        } else GsonTypeAdapters.STRING_FACTORY
        val stringBuilderFactory = if (includeNullString) {
            GsonTypeAdapters.STRING_BUILDER_INCLUDE_NULL_STRING_FACTORY
        } else GsonTypeAdapters.STRING_BUILDER_FACTORY
        val stringBufferFactory = if (includeNullString) {
            GsonTypeAdapters.STRING_BUFFER_INCLUDE_NULL_STRING_FACTORY
        } else GsonTypeAdapters.STRING_BUFFER_FACTORY
        return Gson().newBuilder()
            .registerSafeTypeAdapters(
                stringAdapterFactory = stringFactory,
                stringBuilderFactory = stringBuilderFactory,
                stringBufferFactory = stringBufferFactory
            )
            .registerSafeChinaStyleTypeAdapters()
            .create()
    }
}