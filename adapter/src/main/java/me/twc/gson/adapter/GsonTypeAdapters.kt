@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package me.twc.gson.adapter

import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.internal.bind.TypeAdapters
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import me.twc.gson.adapter.utils.logD
import java.math.BigDecimal
import java.math.BigInteger

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
}

class LongTypeAdapter : TypeAdapter<Number>() {
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

class FloatTypeAdapter : TypeAdapter<Number>() {
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

class DoubleTypeAdapter : TypeAdapter<Number>() {
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

class StringTypeAdapter : TypeAdapter<String>() {
    override fun write(out: JsonWriter, value: String) {
        out.value(value)
    }

    override fun read(read: JsonReader): String {
        if (read.peek() == JsonToken.NULL) {
            read.nextNull()
            return ""
        } else {
            return try {
                val str = read.nextString()
                if (str == "null" || str == "NULL") {
                    ""
                } else {
                    str
                }
            } catch (th: Throwable) {
                logD("StringTypeAdapter read err", th)
                read.skipValue()
                ""
            }
        }
    }
}

class BigDecimalTypeAdapter : TypeAdapter<BigDecimal>() {
    override fun write(out: JsonWriter, value: BigDecimal) {
        out.value(value)
    }

    override fun read(read: JsonReader): BigDecimal {
        val defaultValue = BigDecimal.valueOf(0.0)
        return when (read.peek()) {
            JsonToken.NULL -> {
                read.nextNull()
                defaultValue
            }
            else -> {
                try {
                    BigDecimal(read.nextString())
                } catch (th: Throwable) {
                    logD("BooleanTypeAdapter read err", th)
                    if (th !is NumberFormatException) {
                        read.skipValue()
                    }
                    defaultValue
                }
            }
        }
    }
}

class BigIntegerTypeAdapter : TypeAdapter<BigInteger>() {
    override fun write(out: JsonWriter, value: BigInteger?) {
        out.value(value)
    }

    override fun read(read: JsonReader): BigInteger {
        val defaultValue = BigInteger.valueOf(0)
        return when (read.peek()) {
            JsonToken.NULL -> {
                read.nextNull()
                defaultValue
            }
            else -> {
                try {
                    BigInteger(read.nextString())
                } catch (th: Throwable) {
                    if (th !is NumberFormatException) {
                        read.skipValue()
                    }
                    defaultValue
                }
            }
        }
    }
}