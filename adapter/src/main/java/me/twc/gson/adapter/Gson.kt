package me.twc.gson.adapter

import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory

/**
 * @author 唐万超
 * @date 2021/04/23
 */
fun GsonBuilder.registerSafeTypeAdapters(
    booleanAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.BOOLEAN_FACTORY,
    byteAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.BYTE_FACTORY,
    shortAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.SHORT_FACTORY,
    integerAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.INTEGER_FACTORY,
    longAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.LONG_FACTORY,
    floatAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.FLOAT_FACTORY,
    doubleAdapterFactory: TypeAdapterFactory = GsonTypeAdapters.DOUBLE_FACTORY
): GsonBuilder {
    registerTypeAdapterFactory(booleanAdapterFactory)
    registerTypeAdapterFactory(byteAdapterFactory)
    registerTypeAdapterFactory(shortAdapterFactory)
    registerTypeAdapterFactory(integerAdapterFactory)
    registerTypeAdapterFactory(longAdapterFactory)
    registerTypeAdapterFactory(floatAdapterFactory)
    registerTypeAdapterFactory(doubleAdapterFactory)
    return this
}