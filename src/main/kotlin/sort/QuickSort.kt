package sort


fun binarySearch(array: Array<Int>, target: Int) : Int {
    if (array.isEmpty()) {
        return -1
    }
    var left = 0
    var right = array.size - 1
    while (left <= right) {
        var middle = (right + left) / 2
        if (array[middle] == target) {
            return middle
        } else if (array[middle] > target) {
            right = middle - 1
        } else if (array[middle] < target) {
            left = middle + 1
        }
    }
    return -1
}


fun quickSortInPlace(array: Array<Int>, low : Int, high : Int) {
    if(low >= high) {
        return
    }
    //опорный елемент
    var base = array[low]
    //индекс последнего элемента множества меньших опороного, эту границу в цикле будем сдвигать
    var lastLessIndex = low
    for (i in low + 1 .. high) {
        var element = array[i]
        if (element <= base) {
            //увеличиваем индекс последнего элемента множества меньших
            lastLessIndex++
            //после прибавления по факту пока на этом индексе стоит число больше минимального, мы как раз его свапаем с числом меньше опорного
            swap(array, i, lastLessIndex)
        }
    }
    //тут как раз сваем первый элемент - он же опорный и последний меньший
    swap(array, low, lastLessIndex)

    //после получаем картину: элементы меньше опорного -- опортный -- элементы больше опорного
    quickSortInPlace(array, low, lastLessIndex - 1)
    quickSortInPlace(array, lastLessIndex + 1, high)
}


fun quickSort(array: Array<Int>) : Array<Int> {
    if(array.isEmpty() || array.size == 1) {
        return array
            if(array[0] > array[1]) {
                var s = array[0]
                array[0] = array[1]
                array[1] = s
                return array
            }
        return array
    }
    var arraySmaller = Array(array.size, {0})
    var arraySmallerIndex = 0
    var arrayBigger = Array(array.size, {0})
    var arrayBiggerIndex = 0
    var base = array[0]
    for (i in array.indices) {
        if(i == 0) {
            continue
        }
        if(array[i] <= base) {
            arraySmaller[arraySmallerIndex] = array[i]
            arraySmallerIndex++
        } else {
            arrayBigger[arrayBiggerIndex] = array[i]
            arrayBiggerIndex++
        }
    }
    arrayBigger = arrayBigger.copyOfRange(0, arrayBiggerIndex)
    arraySmaller = arraySmaller.copyOfRange(0, arraySmallerIndex)
    return  quickSort(arraySmaller).plus(base).plus(quickSort(arrayBigger))
}


private fun swap(array: Array<Int>, i: Int, j: Int) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}