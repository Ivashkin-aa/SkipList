import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SkipListTest {

    @Test
    fun print() {
        val test = SKipList(10)
        test.add(6)
        test.add(2)
        test.add(4)
        test.printSkipList()
    }

    @Test
    fun empty() {
        val test = SKipList(10)
        assertTrue(test.isEmpty())
        test.add(4)
        assertFalse(test.isEmpty())
    }
}

