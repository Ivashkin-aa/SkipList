import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SkipListTest {

    @Test
    fun add() {
        val test = SKipList()
        test.add(16)
        test.add(2)
        test.add(4)
        test.addAll(listOf(49, 54, 22, 5))
        test.printSkipList()
    }

    @Test
    fun empty() {
        val test = SKipList()
        assertTrue(test.isEmpty())
        test.add(55)
        assertFalse(test.isEmpty())
    }

    @Test
    fun contains() {
        val test = SKipList()
        assertFalse(test.contains(190))
        test.addAll(setOf(190, 55, 12, 43, 22, 9))
        assertTrue(test.contains(190))
        assertTrue(test.containsAll(listOf(190, 9, 12)))
        assertFalse(test.containsAll(listOf(55, 2, 43)))
    }

    @Test
    fun remove() {
        val test = SKipList()
        test.addAll(setOf(46, 12, 21, 65, 88, 1221, 8898, 232))
        test.printSkipList()
        println("------")
        test.remove(65)
        test.removeAll(listOf(9, 21, 8898))
        test.printSkipList()
    }

    @Test
    fun size() {
        val test = SKipList()
        test.addAll(setOf(17, 22, 24, 48, 51, 69, 19))
        test.printSkipList()
        println(test.levelSize(1))
        println(test.levelSize(10))
    }

    @Test
    fun clear() {
        val test = SKipList()
        test.add(32)
        assertFalse(test.isEmpty())
        test.clear()
        assertTrue(test.isEmpty())
    }

    @Test
    fun firstAndLast() {
        val test = SKipList()
        assertEquals(null, test.first())
        assertEquals(null, test.last())
        test.addAll(setOf(45, 2, 36, 16))
        assertEquals(2, test.first())
        assertEquals(45, test.last())
    }

    @Test
    fun sets() {
        val test = SKipList()
        test.addAll(setOf(12, 42, 55, 12, 222, 36))
        test.tailSet(40).printSkipList()
        println("-----")
        test.headSet(55).printSkipList()
        println("-----")
        test.subSet(20, 100).printSkipList()
    }

    @Test
    fun retain() {
        val test = SKipList()
        test.addAll(setOf(42,12,5,675,124,879,1345,11,5542,1))
        test.printSkipList()
        println("-----")
        test.retainAll(setOf(124,5,675,1))
        test.printSkipList()
    }
}

