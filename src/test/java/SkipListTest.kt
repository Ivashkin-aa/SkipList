import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SkipListTest {

    @Test
    fun print() {
        val test = SKipList()
        test.add(16)
        test.add(2)
        test.add(4)
        test.addAll(listOf(49,54,22,5))
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
        test.add(190)
        assertTrue(test.contains(190))
    }

    @Test
    fun remove() {
        val test = SKipList()
        test.addAll(setOf(46,12,21,65,88,1221,8898,232))
        test.printSkipList()
        println("------")
        test.remove(65)
        test.remove(9)
        test.printSkipList()
    }

}

