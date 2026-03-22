package req

fun sum(array : Array<Int>) : Int {
    return if (array.isEmpty()) {
        0
    } else {
        array[0] + sum(array.copyOfRange(1, array.size))
    }
}


fun count(array : Array<Int>) : Int {
    return if (array.isEmpty()) {
        0
    } else {
        1 + count(array.copyOfRange(1, array.size))
    }
}

fun max(element : Int, array : Array<Int>) : Int {
    if (array.isEmpty()) {
        return element
    }
    if (element > array[0]) {
        return max(element, array.copyOfRange(1, array.size))
    }
    else {
        return max(array[0], array.copyOfRange(1, array.size))
    }
}