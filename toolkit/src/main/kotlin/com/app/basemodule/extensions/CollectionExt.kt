package com.app.basemodule.extensions

fun <E> MutableList<E>.doWhenIndexIsSafe(elem: E, modification: MutableList<E>.(index: Int) -> Unit) {
    val index = this.indexOf(elem)
    if (index >= 0) {
        this.modification(index)
    }
    /** Bezapsnoe Naxojdeniya index po elementu
    getDataSet().doWhenIndexIsSafe(element) { index ->
    if (index > 0) {
    add(index -1, element)
    notifyItemInserted(index -1)
    }
    }

     */
}

fun <T> ArrayList<T>?.notSimilarByHash(array: Any?): Boolean {
    return (this ?: ArrayList()).hashCode() != array?.hashCode()
}

fun <T> List<T>?.notSimilarByHash(array: Any?): Boolean {
    return (this ?: ArrayList()).hashCode() != array?.hashCode()
}