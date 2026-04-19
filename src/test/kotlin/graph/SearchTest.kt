package graph

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SearchTest {
    @Test
    fun search() {
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

        search(graph0) shouldBe 6
        search(graph1) shouldBe 8
        search(graph2) shouldBe 60
    }
}