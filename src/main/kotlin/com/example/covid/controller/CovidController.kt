package com.example.covid.controller

import com.example.covid.model.Covid
import jakarta.websocket.server.PathParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CovidController {

    private val covidResults = listOf(
        Covid(1, "country1", 574, 45, 7000),
        Covid(2, "country2", 236, 123, 1636),
        Covid(3, "country3", 134, 455, 84),
        Covid(4, "country4", 595, 694, 1020),
        Covid(5, "country5", 94, 444, 2923),
        Covid(6, "country6", 24, 87, 8264),
        Covid(7, "country7", 494, 459, 1728),
        Covid(8, "country8", 984, 11, 405),
    )

    @GetMapping("/covid")
    fun covid(): List<Covid> {
        return covidResults
    }

    @GetMapping("/covid/{id}")
    fun getCovidById(@PathVariable id: Int): Covid {
        return covidResults[id - 1]
    }

    @GetMapping("/covid/top5")
    fun getCovidByTopFive(@RequestParam by: String): List<Covid> {
        return covidResults
    }

}
