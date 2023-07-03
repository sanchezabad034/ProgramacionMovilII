package edu.mx.unipolidgo.myfirebaseproject

import java.io.Serializable

data class Product (
    val name: String = "",
    val price: Double = 0.0,
    val Description: String = "",
    val Size: String ="",
    val imageUrl: String = ""
    ):Serializable