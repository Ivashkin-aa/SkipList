class SKipList {
    private var inf: Int = 0
    private var head: SkipNode? = null
    private var bottom: SkipNode? = null
    private var tail: SkipNode? = null

    constructor(inf: Int) {
        this.inf = inf
        bottom = SkipNode(0)
        bottom!!.right = bottom
        bottom!!.down = bottom
        tail = SkipNode(inf)
        tail!!.right = tail
        head = SkipNode(inf, tail!!, bottom!!)
    }

    fun add(x: Int) {
        var current = head
        bottom?.element = x
        while (current != bottom) {
            while (current?.element!! < x)
                current = current.right
            if (current.down?.right?.right?.element!! < current.element) {
                current.right = SkipNode(current.element, current.right!!, current.down?.right?.right!!)
                current.element = current.down?.right?.element!!
            } else
                current = current.down
        }
        if (head?.right != tail)
            head = SkipNode(inf, tail!!, head!!)
    }

    fun isEmpty(): Boolean {
        return head!!.right == tail && head!!.down == bottom
    }

    fun printSkipList() {
        println("SkipList= ")
        var current = head
        while (current?.down != bottom)
            current = current?.down
        while (current?.right != tail) {
            print(current!!.element)
            print(" ")
            current = current.right
        }
    }
}