package com.bdc.hilt_ejemplo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.bdc.hilt_ejemplo.viewmodel.MainViewModel

/** hiltViewModel() creará y solicitará un viewModel.
 * Usando Hilt los va a asociar automáticamente al navBackStackEntry si estamos usan NavHost pero si
 * no, lo asocia al ciclo de vida de la aplicación.
 * En resumen, el ViewModel va a funcionar incluso si usamos Navigation Compose y el ViewModel queda
 * vinculado al ciclo de vida de la actividad.
 * ¿Qué pasaría si llamamos a la pantalla principal desde otro lugar? Ya no es necesario pasar el
 * viewModel como parámetro porque por defecto va a usar el que devuelve Hilt.
 *
 * Cuando terminamos de diseñar el @Composable, tenemos que ir a Manifest para habilitar los permisos
 * de INTERNET y además añadir en <application /> la línea android:name = ".MyApplication". Esto es
 * necesario cuando usamos Hilt, porque necesita saber cuál es la clase principal de la aplicación
 * para poder inicializar el grafo de dependencias. Si no añadimos esta dependencia, Hilt no podría
 * inicializarse
 *
 *
 *
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(
    viewModel: MainViewModel = hiltViewModel()
) {

    //Compose recopila el último dato enviado y para convertirlo en estado se usa collectAsState()
    val usuarios by viewModel.usuarios.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Usuarios") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            if (usuarios.isEmpty()) {
                CircularProgressIndicator()
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    usuarios.forEach { usuario ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                //Imagen del usuario en forma circular
                                AsyncImage(
                                    model = usuario.imagenUrl,
                                    contentDescription = "Imagen de ${usuario.nombre}",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(16.dp))

                                Column {
                                    Text(
                                        text = usuario.nombre,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = usuario.correo,
                                        style = MaterialTheme.typography.bodyMedium
                                    )

                                    Text(
                                        text = "Edad: ${usuario.edad}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}