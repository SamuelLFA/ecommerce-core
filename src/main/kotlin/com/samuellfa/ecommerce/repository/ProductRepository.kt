package com.samuellfa.ecommerce.repository

import com.samuellfa.ecommerce.model.Product
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProductRepository : CrudRepository<Product, UUID>