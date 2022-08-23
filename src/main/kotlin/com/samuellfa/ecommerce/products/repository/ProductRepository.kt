package com.samuellfa.ecommerce.products.repository

import com.samuellfa.ecommerce.products.model.Product
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProductRepository : CrudRepository<Product, UUID>