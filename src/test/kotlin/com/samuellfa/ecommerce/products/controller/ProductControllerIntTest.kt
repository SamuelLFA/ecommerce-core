package com.samuellfa.ecommerce.products.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.samuellfa.ecommerce.products.IntTestBase
import com.samuellfa.ecommerce.products.controller.dto.NewProductInput
import com.samuellfa.ecommerce.products.controller.dto.NewProductResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import java.math.BigDecimal

internal class ProductControllerIntTest : IntTestBase() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper


    @Nested
    inner class `save products` {
        @Test
        fun `when input is valid should return product created`() {
            val input = NewProductInput("product", BigDecimal.valueOf(10.50), 15)
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
            val input = NewProductInput("", BigDecimal.valueOf(10.50), 15)
            val inputJSON = mapper.writeValueAsString(input)
            val request = requestBuilder.post("/products").content(inputJSON)

            val response = mockMvc.perform(request).andReturn().response

            assertEquals(HttpStatus.BAD_REQUEST.value(), response.status)
        }

        @Test
        fun `when price is negative should return 400`() {
            val input = NewProductInput("product", BigDecimal.valueOf(-10.50), 15)
            val inputJSON = mapper.writeValueAsString(input)
            val request = requestBuilder.post("/products").content(inputJSON)

            val response = mockMvc.perform(request).andReturn().response

            assertEquals(HttpStatus.BAD_REQUEST.value(), response.status)
        }

        @Test
        fun `when quantity is negative should return 400`() {
            val input = NewProductInput("product", BigDecimal.valueOf(10.50), -15)
            val inputJSON = mapper.writeValueAsString(input)
            val request = requestBuilder.post("/products").content(inputJSON)

            val response = mockMvc.perform(request).andReturn().response

            assertEquals(HttpStatus.BAD_REQUEST.value(), response.status)
        }
    }

    @Nested
    inner class `get by id products` {
        @Test
        fun `when id is invalid should return 404`() {
            val id = "invalid uuid"
            val request = requestBuilder.get("/products/${id}")

            val response = mockMvc.perform(request).andReturn().response

            assertEquals(HttpStatus.NOT_FOUND.value(), response.status)
        }
    }
}