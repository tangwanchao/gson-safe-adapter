package me.twc.gson.adapter

/**
 * @author 唐万超
 * @date 2021/04/26
 */
data class KotlinArrayData(
    val arr1: Array<String>,
    val arr2: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KotlinArrayData

        if (!arr1.contentEquals(other.arr1)) return false
        if (!arr2.contentEquals(other.arr2)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = arr1.contentHashCode()
        result = 31 * result + arr2.contentHashCode()
        return result
    }
}