package com.example.springbootkt

import com.example.springbootkt.dao.CategoryRepository
import com.example.springbootkt.dao.ProductRepository
import com.example.springbootkt.data.Category
import com.example.springbootkt.data.Product
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*
import java.util.function.Consumer

@SpringBootApplication
class SpringbootktApplication: CommandLineRunner{

    @Autowired
    var productRepository: ProductRepository? = null

    @Autowired
    var categoryRepository: CategoryRepository? = null

    override fun run(vararg args: String) {

        categoryRepository!!.save(Category(null, "Computers", null, null))
        categoryRepository!!.save(Category(null, "Printers", null, null))
        categoryRepository!!.save(Category(null, "Smartphones", null, null))
        val rnd = Random()
        categoryRepository!!.findAll().forEach(Consumer { category: Category? ->
            for (i in 0..9) {
                val p = Product()
                p.name= RandomString.make(10)
                p.availabe=rnd.nextBoolean()
                p.currentPrice= rnd.nextInt(999).toDouble()
                p.promotion=rnd.nextBoolean()
                p.selected=rnd.nextBoolean()
                p.category=category
                p.photoName="unknown.png"
                productRepository!!.save(p)
            }
        })
    }

    }

fun main(args: Array<String>) {
    runApplication<SpringbootktApplication>(*args)
}