package com.donatas.dprofile.http.models

import kotlin.jvm.JvmName

data class QueryParam(
    val key: String,
    val value: String,
    val encoded: Boolean
) {
    override fun equals(other: Any?): Boolean {
        return other is QueryParam && other.key == key
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }
}

class QueryParams {
    private val _parameters = mutableSetOf<QueryParam>()
    val parameters: Set<QueryParam>
        get() = _parameters

    fun put(key: String, value: Int?, encoded: Boolean) {
        value?.let {
            _parameters.add(QueryParam(key = key, value = value.toString(), encoded = encoded))
        }
    }

    fun put(key: String, value: String?, encoded: Boolean) {
        value?.let {
            _parameters.add(QueryParam(key = key, value = value, encoded = encoded))
        }
    }

    /** Creates comma-separated [QueryParams] from given [value] list
     * For example, when given [value] is ["value1", "value2"]
     * It will be converted into "value1,value2" String
     */
    @JvmName("queryParamsPutStringList")
    fun put(key: String, value: List<String>, encoded: Boolean) {
        if (value.isNotEmpty()) {
            _parameters.add(QueryParam(key = key, value = value.joinToString(","), encoded = encoded))
        }
    }

    /** Creates comma-separated [QueryParams] from given [value] list
     * For example, when given [value] is ["value1", "value2"]
     * It will be converted into "value1,value2" String
     */
    @JvmName("queryParamsPutIntList")
    fun put(key: String, value: List<Int>, encoded: Boolean) {
        if (value.isNotEmpty()) {
            _parameters.add(QueryParam(key = key, value = value.joinToString(","), encoded = encoded))
        }
    }

    operator fun plus(params: QueryParams): QueryParams {
        _parameters.addAll(params._parameters)

        return this
    }

    override fun equals(other: Any?): Boolean {
        return (other is QueryParams) && parameters == other.parameters
    }

    override fun hashCode(): Int {
        return _parameters.hashCode()
    }

    override fun toString(): String {
        return parameters.toString()
    }
}
