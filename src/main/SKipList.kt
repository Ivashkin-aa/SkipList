import java.util.*
import kotlin.random.Random

class SKipList<T : Comparable<T>> : SortedSet<T> {
    private var head = SkipNode<T>(null, 33)
    private var levels = 1

    override val size: Int
        get() = sizeForAllLevel()

    override fun add(element: T): Boolean {
        var level = 0
        var random = Random.nextInt()
        while (random and 1 == 1) {
            level++
            if (level == levels) {
                levels++
                break
            }
            random = random shr 1
        }
        val node = SkipNode(element, level + 1)
        var cur = head
        for (i in levels - 1 downTo 0) {
            while (cur.next[i] != null) {
                if (cur.next[i]!!.value!! > element) {
                    break
                }
                cur = cur.next[i]!!
            }
            if (i <= level) {
                node.next[i] = cur.next[i]
                cur.next[i] = node
            }
        }
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements)
            add(element)
        return true
    }

    override fun contains(element: T): Boolean {
        var cur = head
        for (i in levels - 1 downTo 0) {
            while (cur.next[i] != null) {
                if (cur.next[i]?.value == element)
                    return true
                if (cur.next[i]?.value!! > element)
                    break
                cur = cur.next[i]!!
            }
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (element in elements)
            if (!contains(element))
                return false
        return true
    }

    override fun isEmpty(): Boolean {
        return head.next[0] == null
    }

    override fun remove(element: T): Boolean {
        var cur = head
        var found = false
        for (i in levels - 1 downTo 0) {
            while (cur.next[i] != null) {
                if (cur.next[i]?.value == element) {
                    found = true
                    cur.next[i] = cur.next[i]?.next?.get(i)
                    break
                }
                if (cur.next[i]?.value!! > element)
                    break
                cur = cur.next[i]!!
            }
        }
        return found
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        for (element in elements)
            remove(element)
        return true
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        for (i in levels - 1 downTo 0) {
            var cur = head
            while (cur.next[i] != null) {
                if (!elements.contains(cur.next[i]?.value))
                    cur.next[i] = cur.next[i]?.next?.get(i)
                else
                    cur = cur.next[i]!!
            }
        }
        for (i in levels - 1 downTo 0)
            if (levelSize(i) == 0 || (levelSize(i) == 1 && levelSize(i - 1) == 1))
                levels--
        return true
    }

    override fun clear() {
        for (i in levels - 1 downTo 0) {
            var cur = head
            while (cur.next[i] != null) {
                cur.next[i] = null
                if (cur.next[i]?.next?.get(i) == null)
                    break
                else
                    cur = cur.next[i]!!
            }
        }
        levels = 1
    }

    fun levelSize(level: Int): Int {
        var cur = head
        var size = 0
        while (cur.next[level] != null) {
            size++
            cur = cur.next[level]!!
        }
        return size
    }

    private fun sizeForAllLevel(): Int {
        var size = 0
        for (i in levels - 1 downTo 0) {
            size += levelSize(i)
        }
        return size
    }

    override fun first(): T? {
        return head.next[0]?.value
    }

    override fun last(): T? {
        var cur = head
        while (cur.next[0]?.next?.get(0) != null)
            cur = cur.next[0]!!
        return cur.next[0]?.value
    }

    override fun tailSet(fromElement: T): SKipList<T> {
        val sl = SKipList<T>()
        var cur = head
        while (cur.next[0] != null) {
            if (cur.next[0]!!.value!! > fromElement)
                sl.add(cur.next[0]!!.value!!)
            cur = cur.next[0]!!
        }
        return sl
    }

    override fun headSet(toElement: T): SKipList<T> {
        val sl = SKipList<T>()
        var cur = head
        while (cur.next[0] != null) {
            if (cur.next[0]!!.value!! < toElement)
                sl.add(cur.next[0]!!.value!!)
            cur = cur.next[0]!!
        }
        return sl
    }

    override fun subSet(fromElement: T, toElement: T): SKipList<T> {
        val sl = SKipList<T>()
        sl.addAll(headSet(toElement).intersect(tailSet(fromElement)))
        return sl
    }

    override fun iterator(): MutableIterator<T> = object : MutableIterator<T> {
        var cur = head
        var i = levels - 1

        override fun hasNext(): Boolean {
            return i >= 0 && cur.next[i] != null
        }

        override fun next(): T {
            if (!hasNext())
                throw NoSuchElementException()
            return cur.next[i]!!.value!!.also {
                cur = cur.next[i]!!
                if (cur.next[i] == null) {
                    cur = head
                    i--
                }
            }
        }

        override fun remove() {
            remove(cur.value)
        }

    }

    override fun comparator(): Comparator<in T>? = Comparator { o1, o2 ->
        if (o1 == null || o2 == null)
            throw NullPointerException()
        o1.compareTo(o2)
    }

    fun printSkipList() {
        println("SkipList:")
        for (i in levels - 1 downTo 0) {
            var cur = head
            while (cur.next[i] != null) {
                print(cur.next[i]?.value)
                print(" ")
                cur = cur.next[i]!!
            }
            println()
        }
    }
}

