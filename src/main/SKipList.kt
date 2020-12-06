import kotlin.random.Random

class SKipList {
    private var head = SkipNode(0, 33)
    private var levels = 1

    fun add(value: Int) {
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
        val node = SkipNode(value, level + 1)
        var cur = head
        for (i in levels - 1 downTo 0) {
            while (cur.next[i] != null) {
                if (cur.next[i]!!.value > value) {
                    break
                }
                cur = cur.next[i]!!
            }
            if (i <= level) {
                node.next[i] = cur.next[i]
                cur.next[i] = node
            }
        }

    }

    fun addAll(collection: Collection<Int>) {
        for (element in collection)
            add(element)
    }

    fun contains(value: Int): Boolean {
        var cur = head
        for (i in levels - 1 downTo 0) {
            while (cur.next[i] != null) {
                if (cur.next[i]?.value == value)
                    return true
                if (cur.next[i]?.value!! > value)
                    break
                cur = cur.next[i]!!
            }
        }
        return false
    }

    fun isEmpty(): Boolean {
        return head.next[0] == null
    }

    fun remove(value: Int) {
        var cur = head
        for (i in levels - 1 downTo 0) {
            while (cur.next[i] != null) {
                if (cur.next[i]?.value == value) {
                    cur.next[i] = cur.next[i]?.next?.get(i)
                    break
                }
                if (cur.next[i]?.value!! > value)
                    break
                cur = cur.next[i]!!
            }
        }
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
