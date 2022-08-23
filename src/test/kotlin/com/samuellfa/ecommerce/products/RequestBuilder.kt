package com.samuellfa.ecommerce.products

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

class RequestBuilder {

    fun post(url: String) = MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON)

    fun get(url: String) = MockMvcRequestBuilders.get(url)
        .contentType(MediaType.APPLICATION_JSON)
}