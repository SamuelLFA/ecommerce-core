package com.samuellfa.ecommerce.products.controller.dto

import com.samuellfa.ecommerce.products.model.Product
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Digits
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class GetProductResponse (
    @field:NotBlank
    @field:Size(min = 1, max = 255)
    val info: String,
    @field:DecimalMin(value = "0.0", inclusive = true)
    @field:Digits(integer=9, fraction=2)
    val price: BigDecimal
) {
    constructor(product: Product) : this(product.info, product.price)
}