package main.kotlin.stockWatcher
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

class ResponseReader() {
    private val mapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

    init {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun parseStockList(json: String): List<Stock> {
        return mapper.readValue(json)
    }
}