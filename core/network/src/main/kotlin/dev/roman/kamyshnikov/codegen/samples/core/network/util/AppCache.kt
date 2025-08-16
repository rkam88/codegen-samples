package dev.roman.kamyshnikov.codegen.samples.core.network.util

interface AppCache<Key, Value> {
    fun save(key: Key, data: Value)
    fun get(key: Key): Value?
    fun clear()
}
