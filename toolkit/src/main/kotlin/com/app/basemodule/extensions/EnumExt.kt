package com.app.basemodule.extensions

inline fun <reified T : Enum<T>> deserialize(string: String): T? = enumValues<T>().find { it.name.toLowerCase() == string.toLowerCase() }
inline fun <reified T : Enum<T>> find(predicate: (T) -> Boolean): T? = enumValues<T>().find { predicate(it) }
fun <T : Enum<T>> Enum<T>.serialize(): String = this.name.toLowerCase()
operator fun <T : Enum<T>> Enum<T>.component1(): String? = this.name.toLowerCase()
