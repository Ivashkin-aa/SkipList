class SkipNode<T:Comparable<T>>(var value: T?, level: Int) {
    var next: Array<SkipNode<T>?> = arrayOfNulls(level)
}