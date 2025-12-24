package com.bdc.hilt_ejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bdc.hilt_ejemplo.ui.theme.HiltEjemploTheme
import com.bdc.hilt_ejemplo.view.PantallaPrincipal
import dagger.hilt.android.AndroidEntryPoint

/** Con la anotación @AndroidEntryPoint le dice a Hilt que se prepare para
 * inyectar dependencias en esta clase cuando sea necesario.
 * Si no utilizamos esta notación, no podremos inyectar dependencias
 * que provienen de los ViewModels, repositorios u otros objetos en esta
 * actividad.
 * Esta anotación generará automaticamente el código necesario para
 * inyectar dependencias durante la compilación.
 *
 * En resumen, si nuestra pantalla viene con inyección de dependencias y la
 * estamos llamando en una actividad que seria su padre, debemos especificar a
 * Hilt que esta clase va a utilizar inyección de dependencias.
 *
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HiltEjemploTheme {
                PantallaPrincipal()
            }
        }
    }
}

