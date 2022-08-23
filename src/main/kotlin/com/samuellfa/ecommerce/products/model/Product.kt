package com.samuellfa.ecommerce.products.model

import java.math.BigDecimal
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
data class Product (
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID?,
    val info: String,
    val price: BigDecimal
)