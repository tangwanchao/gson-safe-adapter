package me.twc.gson.adapter

import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory

/**
 * @author 唐万超
 * @date 2021/04/23
 */


/**
 * @see GsonTypeAdapters.STRING_INCLUDE_NULL_STRING_FACTORY
 * @see GsonTypeAdapters.STRING_BUILDER_INCLUDE_NULL_STRING_FACTORY
 * @see GsonTypeAdapters.STRING_BUFFER_INCLUDE_NULL_STRING_FACTORY
 */
fun GsonBuilder.registerSafeTypeAdapters(
    booleanAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.BOOLEAN_FACTORY,
    byteAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.BYTE_FACTORY,
    shortAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.SHORT_FACTORY,
    integerAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.INTEGER_FACTORY,
    longAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.LONG_FACTORY,
    floatAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.FLOAT_FACTORY,
    doubleAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.DOUBLE_FACTORY,
    stringAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.STRING_FACTORY,
    stringBuilderFactory: TypeAdapterFactory = GsonTypeAdapters.STRING_BUILDER_FACTORY,
    stringBufferFactory: TypeAdapterFactory = GsonTypeAdapters.STRING_BUFFER_FACTORY,
    bigIntegerFactory: TypeAdapterFactory = GsonTypeAdapters.BIG_INTEGER_FACTORY,
    bigDecimalFactory: TypeAdapterFactory = GsonTypeAdapters.BIG_DECIMAL_FACTORY,
    collectionTypeAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.COLLECTION_ADAPTER_FACTORY,
    arrayTypeAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.ARRAY_FACTORY
): GsonBuilder {
    registerTypeAdapterFactory(booleanAdapterFactory)
    registerTypeAdapterFactory(byteAdapterFactory)
    registerTypeAdapterFactory(shortAdapterFactory)
    registerTypeAdapterFactory(integerAdapterFactory)
    registerTypeAdapterFactory(longAdapterFactory)
    registerTypeAdapterFactory(floatAdapterFactory)
    registerTypeAdapterFactory(doubleAdapterFactory)
    registerTypeAdapterFactory(stringAdapterFactory)
    registerTypeAdapterFactory(stringBuilderFactory)
    registerTypeAdapterFactory(stringBufferFactory)
    registerTypeAdapterFactory(bigIntegerFactory)
    registerTypeAdapterFactory(bigDecimalFactory)
    registerTypeAdapterFactory(collectionTypeAdapterFactory)
    registerTypeAdapterFactory(arrayTypeAdapterFactory)
    return this
}