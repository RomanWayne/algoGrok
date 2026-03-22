package graph

fun search(
    startName: String,
    graph: Map<String, List<String>>,
    predicate: (String) -> Boolean = { it.startsWith("S") }
): Boolean {
    if (!graph.containsKey(startName)) return false

    val checked = mutableSetOf<String>()
    val queue = ArrayDeque<String>()

    queue.add(startName)

    while (queue.isNotEmpty()) {
        val person = queue.removeFirst()
        if (person in checked) continue

        checked.add(person)

        if (predicate(person)) return true

        graph[person]?.forEach { friend ->
            if (friend !in checked) {
                queue.add(friend)
            }
        }
    }

    return false
}