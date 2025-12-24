package com.bdc.hilt_ejemplo.data

import com.bdc.hilt_ejemplo.model.Usuario

//Simula una fuente de datos externa, como por ejemplo lo que recibiríamos de una API o una base de datos

class RepositorioSimulado  {

    //Creamos la función que devuelve una lista de usuarios simulados
    //Buscamos las imágenes en pixabay
    fun obtenerUsuarios() : List<Usuario> {
        return listOf(
            Usuario(
                id = 1,
                nombre = "Juan Pérez",
                correo = "juan@gmail.com",
                edad = 22,
                imagenUrl = "https://cdn.pixabay.com/photo/2025/10/07/10/59/parrot-9878922_1280.jpg"
            ),
            Usuario(
                id = 2,
                nombre = "Ana Torres",
                correo = "ana@gmail.com",
                edad = 20,
                imagenUrl = "https://cdn.pixabay.com/photo/2025/09/29/07/22/kitten-9861764_1280.jpg"
            ),
            Usuario(
                id = 3,
                nombre = "Luis Gómez",
                correo = "luis@gmail.com",
                edad = 25,
                imagenUrl = "https://cdn.pixabay.com/photo/2025/11/06/10/17/garden-lizard-9940372_1280.jpg"
            )
        )
    }


}