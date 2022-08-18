package com.samuellfa.ecommerce.controller.dto

import com.samuellfa.ecommerce.model.Product
import java.math.BigDecimal
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Digits
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank

data class NewProductResponse (
    @NotBlank @Max(255)
    val info: String,
    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer=9, fraction=2)
    val price: BigDecimal
) {
    constructor(product: Product) : this(product.info, product.price)
}