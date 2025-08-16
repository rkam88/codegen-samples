package dev.roman.kamyshnikov.codegen.samples.core.network.util

class InMemoryCache<Key, Value>(
    private val dateTimeUtils: DateTimeUtils,
) : AppCache<Key, Value> {

    private val map = mutableMapOf<Key, Value>()

    override fun save(key: Key, data: Value) {
        map[key] = data
    }

    override fun get(key: Key): Value? {
        return map[key]
    }

    override fun clear() = map.clear()
}
