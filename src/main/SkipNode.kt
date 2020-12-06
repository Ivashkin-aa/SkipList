class SkipNode(var value: Int, level: Int) {
    var next: Array<SkipNode?> = arrayOfNulls(level)
}