package de.tbuss.shop.cart

import org.springframework.web.bind.annotation.*

@RestController
class CartController(private var products: Map<Long, ArrayList<Product>> = mapOf()) {

    @GetMapping("/{userId}/products")
    fun getProducts(@PathVariable userId: Long): List<Product> = products[userId].orEmpty()

    @PostMapping("/{userId}/products")
    fun addProductToCart(@PathVariable userId: Long, @RequestBody product: Product) {
        val listOfProducts = products[userId]
        if (listOfProducts != null && !listOfProducts.contains(product))
            listOfProducts.add(product)
        else {
            val list = ArrayList<Product>()
            list.add(product)
            products = products.plus(Pair(userId, list))
        }
    }
}
