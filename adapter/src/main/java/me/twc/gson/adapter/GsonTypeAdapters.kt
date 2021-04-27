@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package me.twc.gson.adapter

import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.internal.ConstructorConstructor
import com.google.gson.internal.bind.TypeAdapters
import com.google.gson.internal.bind.util.ISO8601Utils
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import me.twc.gson.adapter.utils.logD
import java.math.BigDecimal
import java.math.BigInteger
import java.sql.Timestamp
import java.text.DateFormat
import java.text.ParseException
import java.text.ParsePosition
import java.util.*

/**
 * @author 唐万超
 * @date 2021/04/23
 */

object GsonTypeAdapters {
    //<editor-fold desc="boolean">
    /**
     * @see TypeAdapters.BOOLEAN
     */
    val BOOLEAN = object : TypeAdapter<Boolean>() {

        val defaultValue = false

        override fun write(writer: JsonWriter, value: Boolean) {
            writer.value(value)
        }

        override fun read(reader: JsonReader): Boolean {
            return when (reader.peek()) {
                JsonToken.STRING -> {
                    reader.nextString()?.toBoolean() ?: defaultValue
                }
                else -> {
                    try {
                        reader.nextBoolean()
                    } catch (th: Throwable) {
                        logD("BooleanTypeAdapter read err", th)
                        reader.skipValue()
                        defaultValue
                    }
                }
            }
        }
    }

    val BOOLEAN_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        Boolean::class.javaPrimitiveType,
        Boolean::class.javaObjectType,
        BOOLEAN
    )
    //</editor-fold>

    //<editor-fold desc="byte">
    val BYTE = object : TypeAdapter<Number>() {

        override fun write(writer: JsonWriter, value: Number) {
            writer.value(value)
        }

        override fun read(reader: JsonReader): Number {
            return try {
                reader.nextInt().toByte()
            } catch (th: Throwable) {
                logD("ByteTypeAdapter read err", th)
                reader.skipValue()
                0.toByte()
            }
        }
    }

    val BYTE_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        Byte::class.javaPrimitiveType,
        Byte::class.javaObjectType,
        BYTE
    )
    //</editor-fold>

    //<editor-fold desc="short">
    val SHORT = object : TypeAdapter<Number>() {

        override fun write(writer: JsonWriter, value: Number) {
            writer.value(value)
        }

        override fun read(reader: JsonReader): Number {
            return try {
                reader.nextInt().toShort()
            } catch (th: Throwable) {
                logD("ShortTypeAdapter read err", th)
                reader.skipValue()
                0.toShort()
            }
        }
    }

    val SHORT_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        Short::class.javaPrimitiveType,
        Short::class.javaObjectType,
        SHORT
    )
    //</editor-fold>

    //<editor-fold desc="integer">
    val INTEGER = object : TypeAdapter<Number>() {
        override fun write(out: JsonWriter, value: Number) {
            out.value(value)
        }

        override fun read(read: JsonReader): Int {
            return try {
                read.nextInt()
            } catch (th: Throwable) {
                logD("IntTypeAdapter read err", th)
                read.skipValue()
                0
            }
        }
    }

    val INTEGER_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        Int::class.javaPrimitiveType,
        Int::class.javaObjectType,
        INTEGER
    )
    //</editor-fold>

    //<editor-fold desc="long">
    val LONG = object : TypeAdapter<Number>() {
        override fun write(out: JsonWriter, value: Number) {
            out.value(value)
        }

        override fun read(read: JsonReader): Number {
            return try {
                read.nextLong()
            } catch (th: Throwable) {
                logD("LongTypeAdapter read err", th)
                read.skipValue()
                0L
            }
        }
    }

    val LONG_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        Long::class.javaPrimitiveType,
        Long::class.javaObjectType,
        LONG
    )
    //</editor-fold>

    //<editor-fold desc="float">
    val FLOAT = object : TypeAdapter<Number>() {
        override fun write(out: JsonWriter, value: Number) {
            out.value(value)
        }

        override fun read(read: JsonReader): Number {
            return try {
                read.nextDouble().toFloat()
            } catch (th: Throwable) {
                logD("FloatTypeAdapter read err", th)
                read.skipValue()
                0F
            }
        }
    }

    val FLOAT_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        Float::class.javaPrimitiveType,
        Float::class.javaObjectType,
        FLOAT
    )
    //</editor-fold>

    //<editor-fold desc="double">
    val DOUBLE = object : TypeAdapter<Number>() {
        override fun write(out: JsonWriter, value: Number) {
            out.value(value)
        }

        override fun read(read: JsonReader): Number {
            return try {
                read.nextDouble()
            } catch (th: Throwable) {
                logD("DoubleTypeAdapter read err", th)
                read.skipValue()
                0.0
            }
        }
    }

    val DOUBLE_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        Double::class.javaPrimitiveType,
        Double::class.javaObjectType,
        DOUBLE
    )
    //</editor-fold>

    //<editor-fold desc="String">
    class StringTypeAdapter(
        private val includeNullString: Boolean = false
    ) : TypeAdapter<String>() {
        override fun write(out: JsonWriter, value: String) {
            if (includeNullString) {
                out.value(value)
            } else {
                out.value(if (value.equals("null", true)) "" else value)
            }
        }

        override fun read(read: JsonReader): String {
            return try {
                val str = read.nextString()
                if (includeNullString || !str.equals("null", true)) {
                    str
                } else ""
            } catch (th: Throwable) {
                logD("StringTypeAdapter read err", th)
                read.skipValue()
                ""
            }
        }
    }

    val STRING = StringTypeAdapter(includeNullString = false)
    val STRING_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        String::class.java,
        STRING
    )

    val STRING_INCLUDE_NULL_STRING = StringTypeAdapter(includeNullString = true)
    val STRING_INCLUDE_NULL_STRING_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        String::class.java,
        STRING_INCLUDE_NULL_STRING
    )
    //</editor-fold>

    //<editor-fold desc="others">

    //<editor-fold desc="StringBuilder">
    class StringBuilderAdapter(
        private val includeNullString: Boolean = false
    ) : TypeAdapter<StringBuilder>() {
        override fun write(writer: JsonWriter, value: StringBuilder) {
            val stringAdapter = if (includeNullString) STRING_INCLUDE_NULL_STRING else STRING
            stringAdapter.write(writer, value.toString())
        }

        override fun read(reader: JsonReader): StringBuilder {
            val stringAdapter = if (includeNullString) STRING_INCLUDE_NULL_STRING else STRING
            return StringBuilder(stringAdapter.read(reader))
        }
    }

    val STRING_BUILDER = StringBuilderAdapter(includeNullString = false)
    val STRING_BUILDER_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        StringBuilder::class.java,
        STRING_BUILDER
    )

    val STRING_BUILDER_INCLUDE_NULL_STRING = StringBuilderAdapter(includeNullString = true)
    val STRING_BUILDER_INCLUDE_NULL_STRING_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        StringBuilder::class.java,
        STRING_BUILDER_INCLUDE_NULL_STRING
    )

    //</editor-fold>

    //<editor-fold desc="StringBuffer">
    class StringBufferAdapter(
        private val includeNullString: Boolean = false
    ) : TypeAdapter<StringBuffer>() {
        override fun write(writer: JsonWriter, value: StringBuffer) {
            val stringAdapter = if (includeNullString) STRING_INCLUDE_NULL_STRING else STRING
            stringAdapter.write(writer, value.toString())
        }

        override fun read(reader: JsonReader): StringBuffer {
            val stringAdapter = if (includeNullString) STRING_INCLUDE_NULL_STRING else STRING
            return StringBuffer(stringAdapter.read(reader))
        }
    }

    val STRING_BUFFER = StringBufferAdapter(includeNullString = false)
    val STRING_BUFFER_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        StringBuffer::class.java,
        STRING_BUFFER
    )

    val STRING_BUFFER_INCLUDE_NULL_STRING = StringBufferAdapter(includeNullString = true)
    val STRING_BUFFER_INCLUDE_NULL_STRING_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        StringBuffer::class.java,
        STRING_BUFFER_INCLUDE_NULL_STRING
    )
    //</editor-fold>

    //<editor-fold desc="BigInteger">
    val BIG_INTEGER = object : TypeAdapter<BigInteger>() {
        override fun write(out: JsonWriter, value: BigInteger) {
            out.value(value.toString())
        }

        override fun read(read: JsonReader): BigInteger {
            return try {
                BigInteger(read.nextString())
            } catch (th: Throwable) {
                logD("BigIntegerTypeAdapter read err", th)
                if (th !is NumberFormatException) {
                    read.skipValue()
                }
                BigInteger.valueOf(0)
            }
        }
    }
    val BIG_INTEGER_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        BigInteger::class.java,
        BIG_INTEGER
    )
    //</editor-fold>

    //<editor-fold desc="BigDecimal">
    val BIG_DECIMAL = object : TypeAdapter<BigDecimal>() {
        override fun write(out: JsonWriter, value: BigDecimal) {
            out.value(value.toPlainString())
        }

        override fun read(read: JsonReader): BigDecimal {
            return try {
                BigDecimal(read.nextString())
            } catch (th: Throwable) {
                logD("BigDecimalTypeAdapter read err", th)
                if (th !is NumberFormatException) {
                    read.skipValue()
                }
                BigDecimal.valueOf(0.0)
            }
        }
    }
    val BIG_DECIMAL_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        BigDecimal::class.java,
        BIG_DECIMAL
    )
    //</editor-fold>

    //<editor-fold desc="Collection">
    val COLLECTION_ADAPTER_FACTORY = CollectionTypeAdapterFactory(ConstructorConstructor(mapOf()))
    //</editor-fold>

    //<editor-fold desc="Array">
    val ARRAY_FACTORY: TypeAdapterFactory = ArrayTypeAdapter.FACTORY
    //</editor-fold>

    //<editor-fold desc="Data">
    val DATE_CHINA_MEDIUM = object : TypeAdapter<Date>() {

        private val dateFormat = DateFormat.getDateTimeInstance(
            DateFormat.MEDIUM,
            DateFormat.MEDIUM,
            Locale.CHINA
        )

        override fun write(writer: JsonWriter, value: Date) {
            writer.value(dateFormat.format(value))
        }

        override fun read(reader: JsonReader): Date {
            if (reader.peek() != JsonToken.STRING) {
                reader.skipValue()
                return Date()
            }
            val nextString = reader.nextString()

            try {
                val parsed = dateFormat.parse(nextString)
                if (parsed != null) return parsed
            } catch (ex: ParseException) {
                // do nothing
            }

            try {
                val parsed = ISO8601Utils.parse(nextString, ParsePosition(0))
                if (parsed != null) return parsed
            } catch (ex: ParseException) {
                // do nothing
            }

            try {
                return Date(nextString.toLong())
            } catch (ex: NumberFormatException) {
                // do nothing
            }
            return Date()
        }
    }

    val DATE_CHINA_MEDIUM_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        Date::class.java,
        DATE_CHINA_MEDIUM
    )
    //</editor-fold>

    //<editor-fold desc="Timestamp">
    /*val TIMESTAMP_CHINA_MEDIUM = object : TypeAdapter<Timestamp>() {
        override fun read(reader: JsonReader): Timestamp {
            val date = DATE_CHINA_MEDIUM.read(reader)
            return Timestamp(date.time)
        }

        override fun write(writer: JsonWriter, value: Timestamp) {
            DATE_CHINA_MEDIUM.write(writer, value)
        }
    }

    val TIMESTAMP_CHINA_MEDIUM_FACTORY: TypeAdapterFactory = TypeAdapters.newFactory(
        Timestamp::class.java,
        TIMESTAMP_CHINA_MEDIUM
    )*/
    //</editor-fold>

    //</editor-fold>
}