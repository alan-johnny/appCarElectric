package com.example.eletriccarapp.data

import com.example.eletriccarapp.domain.Carro

object CarFactory {
    val list = listOf(
        Carro(
            id = 1,
            preco = "R$ 250.000,00",
            bateria = "300 KwH",
            potencia = "350w",
            recarga = "24 min",
            urlPhoto = "https://example.com/image1.jpg"
            ),

        Carro(
            id = 2,
            preco = "R$ 350.000,00",
            bateria = "200 KwH",
            potencia = "150w",
            recarga = "34 min",
            urlPhoto = "https://example.com/image1.jpg"
        ),

        Carro(
            id = 3,
            preco = "R$ 450.000,00",
            bateria = "150 KwH",
            potencia = "200w",
            recarga = "48 min",
            urlPhoto = "https://example.com/image1.jpg"
        ),
        Carro(
            id = 4,
            preco = "R$ 350.000,00",
            bateria = "200 KwH",
            potencia = "150w",
            recarga = "34 min",
            urlPhoto = "https://example.com/image1.jpg"
        ),
        Carro(
            id = 5,
            preco = "R$ 350.000,00",
            bateria = "200 KwH",
            potencia = "150w",
            recarga = "34 min",
            urlPhoto = "https://example.com/image1.jpg"
        )
    )
}