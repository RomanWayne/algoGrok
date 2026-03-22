import graph.search
import req.count
import req.max
import req.sum
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


    // array = arrayOf(1,2,3,4,5, 6)
    println(sum(array))

    println(count(array))

    println(max(array[0], array))

    // Генерация рандомного графа друзей
    val friendsGraph = generateFriendsGraph(numPeople = 5, maxFriendsPerPerson = 3)
    printFriendsGraph(friendsGraph)

    // Примеры поиска с разными условиями
    println("\n=== Примеры поиска ===")
    println("Есть ли друг, начинающийся с 'S': ${search("me", friendsGraph)}")
    println("Есть ли друг 'Alice': ${search("me", friendsGraph) { it == "Alice" }}")
    println("Есть ли друг с именем длиннее 5 букв: ${search("me", friendsGraph) { it.length > 5 }}")
}

// Функция для генерации случайных имен
fun generateRandomNames(count: Int): List<String> {
    val names = listOf(
        "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry",
        "Iris", "Jack", "Kate", "Leo", "Maria", "Nick", "Olivia", "Paul",
        "Quinn", "Rachel", "Steve", "Tina", "Uma", "Victor", "Wendy", "Xavier"
    )
    return names.shuffled().take(count)
}

// Генерация рандомного графа друзей
fun generateFriendsGraph(numPeople: Int, maxFriendsPerPerson: Int): Map<String, List<String>> {
    val graph = mutableMapOf<String, MutableList<String>>()

    // Создаем список всех людей, включая "me" как корень
    val allPeople = mutableListOf("me")
    allPeople.addAll(generateRandomNames(numPeople - 1))

    // Инициализируем граф
    allPeople.forEach { person ->
        graph[person] = mutableListOf()
    }

    // Добавляем случайные связи
    allPeople.forEach { person ->
        val numFriends = (1..maxFriendsPerPerson).random()
        val availableFriends = allPeople.filter { it != person && it !in graph[person]!! }

        val friendsToAdd = availableFriends.shuffled().take(numFriends)
        friendsToAdd.forEach { friend ->
            // Добавляем двустороннюю связь
            if (friend !in graph[person]!!) {
                graph[person]!!.add(friend)
            }
            if (person !in graph[friend]!!) {
                graph[friend]!!.add(person)
            }
        }
    }

    return graph
}

// Вывод графа друзей
fun printFriendsGraph(graph: Map<String, List<String>>) {
    println("\n=== Граф друзей ===")
    println("Корень: me")
    println("\nВсе связи:")
    graph.forEach { (person, friends) ->
        val friendsList = if (friends.isEmpty()) "нет друзей" else friends.joinToString(", ")
        println("$person -> $friendsList")
    }

    // BFS обход от корня "me"
    println("\n=== BFS обход от 'me' ===")
    bfsFriends(graph, "me")
}

// BFS обход графа друзей
fun bfsFriends(graph: Map<String, List<String>>, start: String) {
    val visited = mutableSetOf<String>()
    val queue = ArrayDeque<Pair<String, Int>>()

    queue.add(start to 0)
    visited.add(start)

    while (queue.isNotEmpty()) {
        val (person, level) = queue.removeFirst()
        val indent = "  ".repeat(level)
        println("${indent}Уровень $level: $person")

        graph[person]?.forEach { friend ->
            if (friend !in visited) {
                visited.add(friend)
                queue.add(friend to level + 1)
            }
        }
    }
}
