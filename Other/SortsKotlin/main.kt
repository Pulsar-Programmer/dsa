



fun main(args: Array<String>) {
    println("Hello!");
}

class Hello<T> {
    private var array: Array<Any?>
    private var len: Int = 0
    private var capacity: Int = 0

    companion object {
        private const val DEFAULT_CAPACITY = 10
    }

    // Create a new empty Hello
    constructor() {
        array = arrayOfNulls(DEFAULT_CAPACITY)
        capacity = DEFAULT_CAPACITY
    }
    // Create a new Hello with specified capacity
    constructor(initialCapacity: Int) {
        array = arrayOfNulls(initialCapacity)
        capacity = initialCapacity
    }
    // Push a value to the vector
    fun push(value: T) {
        if (len == capacity) {
            grow(if (capacity == 0) 1 else capacity * 2)
        }
        array[len] = value
        len++
    }

    // Get element at index
    @Suppress("UNCHECKED_CAST")
    fun get(index: Int): T {
        if (index >= len) throw IndexOutOfBoundsException("Index: $index, Size: $len")
        return array[index] as T
    }

    // Get current size
    fun size(): Int = len

    // Get current capacity
    fun capacity(): Int = capacity

    // Grow the capacity of the vector
    private fun grow(newCapacity: Int) {
        val newArray = arrayOfNulls<Any?>(newCapacity)
        array.copyInto(newArray, 0, 0, len)
        array = newArray
        capacity = newCapacity
    }
}
