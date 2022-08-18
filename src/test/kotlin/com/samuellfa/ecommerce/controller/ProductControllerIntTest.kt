package com.samuellfa.ecommerce.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.samuellfa.ecommerce.IntTestBase
import com.samuellfa.ecommerce.controller.dto.NewProductInput
import com.samuellfa.ecommerce.controller.dto.NewProductResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.math.BigDecimal

internal class ProductControllerIntTest : IntTestBase() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun `when input is valid should return product created`() {
        val input = NewProductInput("product", BigDecimal.valueOf(10.50))
        val inputJSON = mapper.writeValueAsString(input)
        val request = requestBuilder.post("/products").content(inputJSON)

        val response = mockMvc.perform(request).andReturn().response
        val responseContent = response.contentAsString
        val responseObject = mapper.readValue(responseContent, NewProductResponse::class.java)

        assertEquals(input.price, responseObject.price)
        assertEquals(input.info, responseObject.info)
    }

    @Test
    fun `when info is empty should return 400`() {
        val input = NewProductInput("", BigDecimal.valueOf(10.50))
        val inputJSON = mapper.writeValueAsString(input)
        val request = requestBuilder.post("/products").content(inputJSON)

        val response = mockMvc.perform(request).andReturn().response

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.status)
    }

    @Test
    fun `when price is negative should return 400`() {
        val input = NewProductInput("product", BigDecimal.valueOf(-10.50))
        val inputJSON = mapper.writeValueAsString(input)
        val request = requestBuilder.post("/products").content(inputJSON)

        val response = mockMvc.perform(request).andReturn().response

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.status)
    }
}