class SkipNode {
    var element: Int = 0
    var right: SkipNode? = null
    var down: SkipNode? = null

    constructor(element: Int) {
        this.element = element
        right = null
        down = null
    }

    constructor(element: Int, right: SkipNode, down: SkipNode) {
        this.element = element
        this.right = right
        this.down = down
    }

}