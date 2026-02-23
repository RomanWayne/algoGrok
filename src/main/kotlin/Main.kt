import kotlin.math.abs
import sort.*

fun main() {
    println("Hello World!")

    var array = arrayOf(7,5,4,8,9,1,21,6,75,66,343)
    quickSortInPlace(array, 0, array.size - 1)
    for (i in array) {
        print(i)
        print(" ")
    }
    // array = quickSort(array)
    println(array[0])
    println(binarySearch(array, 21))


}
