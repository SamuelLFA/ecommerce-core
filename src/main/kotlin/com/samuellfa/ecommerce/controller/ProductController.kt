package com.samuellfa.ecommerce.controller

import com.samuellfa.ecommerce.controller.dto.NewProductInput
import com.samuellfa.ecommerce.controller.dto.NewProductResponse
import com.samuellfa.ecommerce.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("products")
class ProductController(private val repository: ProductRepository) {

    @PostMapping
    fun save(
        @Valid @RequestBody input: NewProductInput,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<NewProductResponse> {
        val model = input.toModel()
        repository.save(model)

        val uri = uriBuilder.path("/{id}").buildAndExpand(model.id.toString()).toUri()
        return ResponseEntity.created(uri).body(NewProductResponse(model))
    }
}