package graph

import kotlin.String
import kotlin.collections.set

fun main() {
    var graph0 = mutableMapOf("start" to mapOf("a" to 6, "b" to 2))
    graph0.put("a", mapOf("fin" to 1))
    graph0.put("b", mapOf(
        "a" to 3,
        "fin" to 5)
    )
    graph0.put("fin", mapOf())


    var graph1 = mapOf(
        "start" to mapOf("A" to 5, "B" to 2),
        "A" to mapOf("C" to 4, "D" to 2),
        "B" to mapOf("A" to 8, "D" to 7),
        "C" to mapOf("D" to 6, "fin" to 3),
        "D" to mapOf("fin" to 1),
        "fin" to mapOf()
    )

    var graph2 = mapOf(
        "start" to mapOf("A" to 10),
        "A" to mapOf("C" to 20),
        "B" to mapOf("A" to 1),
        "C" to mapOf("B" to 1, "fin" to 30),
        "fin" to mapOf()
    )

    var graph3 = mapOf(
        "start" to mapOf("A" to 10),
        "A" to mapOf("C" to 20),
        "B" to mapOf("A" to 1),
        "C" to mapOf("B" to 1, "fin" to 30),
        "fin" to mapOf()
    )

    search(graph0)
    search(graph1)
    search(graph2)
}

fun searchLeaning() {
    //сам граф
    var graph = mutableMapOf("start" to mapOf("a" to 6, "b" to 2))
    graph.put("a", mapOf("fin" to 1))
    graph.put("b", mapOf(
        "a" to 3,
        "fin" to 5)
    )
    graph.put("fin", mapOf())


    var costs = mutableMapOf(
        "a" to 6,
        "b" to 2,
        "fin" to Int.MAX_VALUE
    )

    var parents = mutableMapOf(
        "a" to "start",
        "b" to "start",
        "fin" to null
    )

    var processed = mutableSetOf<String>()

    var nodeKey = findLowestExceptProcessed(costs, processed)
    while (nodeKey != "") {
        var cost = costs[nodeKey]!!
        var neighbors = graph[nodeKey]
        for (n in neighbors!!) {
            var newCost = cost + n.value
            if (costs[n.key]!! > newCost) {
                costs[n.key] = newCost
                parents[n.key] = nodeKey
            }
        }
        processed.add(nodeKey)
        nodeKey = findLowestExceptProcessed(costs, processed)
    }
    print(costs)
    print(parents)



}


fun search(graph : Map<String, Map<String, Int>>) {
    var costs = initCosts(graph)
    var parents = initParents(graph)
    var processed = mutableSetOf<String>()

    var nodeKey = findLowestExceptProcessed(costs, processed)
    while (nodeKey != "") {
        var cost = costs[nodeKey]!!
        var neighbors = graph[nodeKey]
        for (n in neighbors!!) {
            var newCost = cost + n.value
            if (costs[n.key]!! > newCost) {
                costs[n.key] = newCost
                parents[n.key] = nodeKey
            }
        }
        processed.add(nodeKey)
        nodeKey = findLowestExceptProcessed(costs, processed)
    }
    println(costs)
    println(parents)
    println("Answer: " + costs["fin"])
}

fun findLowestExceptProcessed(costs : Map<String, Int>, processed : Set<String>) : String {
    var min = Int.MAX_VALUE
    var minNodeKey = ""
    for (node in costs) {
        if (node.key !in processed && node.value < min) {
            min = node.value
            minNodeKey = node.key
        }
    }
    return minNodeKey
}

fun initCosts(graph : Map<String, Map<String, Int>>) : MutableMap<String, Int> {
    var costs = mutableMapOf<String, Int>()
    costs.putAll(graph["start"]!!)
    for (node in graph) {
        if (node.key !in costs.keys && node.key != "start") {
            costs.put(node.key, Int.MAX_VALUE)
        }
    }
    return costs
}

fun initParents(graph : Map<String, Map<String, Int>>) : MutableMap<String, String?> {
    var parents = mutableMapOf<String, String?>()
    for (n in graph["start"]!!) {
        parents.put(n.key, "start")
    }
    for(node in graph) {
        if (node.key !in parents.keys && node.key != "start") {
            parents[node.key] = null
        }
    }

    return parents
}





