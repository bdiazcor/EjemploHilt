package com.bdc.hilt_ejemplo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bdc.hilt_ejemplo.data.RepositorioSimulado
import com.bdc.hilt_ejemplo.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**Tenemos ViewModel() aunque no hemos instalado la dependencia porque HiltNavigationCompose ya trae
 *la dependencia de ViewModel()
 *Las dependencia se inyectan en el constructor del ViewModel con @Inject. Esto lo que indica es que
 * Hilt será el responsable de proporcionar una instancia de RepositorioSimulado cuando se cree el
 * MainViewModel.
 * Para que esto funcione correctamente, el ViewModel tiene que estar anotado con @HiltViewModel.
 *
 * Con private val repositorio estamos inyectando la dependencia que será el repositorio de datos de
 * los usuarios.
 * Con @Inject estamos diciendo a Hilt que necesitamos que alguien nos proporcione una instancia de
 * RepositorioSimulado. Hilt revisará todos sus módulos anotados con @Module y @InstallIn y buscará
 * una función anotada con @Provides que devuelva una instancia de RepositorioSimulado.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    //@Inject: "necesitamos algo"
    //@Module + @Provides: "Yo te lo doy"
    //@InstallIn: "Estoy disponible para ayudarte a este nivel de la aplicación. El ciclo de vida
    // más alto de Hilt".
    private val repositorio: RepositorioSimulado
): ViewModel() { //Estamos heredando de ViewModel() porque necesitamos el ciclo de vida del ViewModel
    //Es decir que sobreviva a cambios de configuración y maneje la lógica en la interfaz de usuario.

    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())

    //usuarios es la versión pública inmutable de _usuarios. La UI puede observarla pero no modificarla
    val usuarios : StateFlow<List<Usuario>> = _usuarios

    //Bloque de inicialización
    init {
        cargarUsuarios()
    }

    //Creamos una función que simula una llamada a una API remota
    fun cargarUsuarios() {
        //viewModelScope permite ejecutar corrutinas dentro de un viewModel
        viewModelScope.launch {
            delay(4000)
            _usuarios.value = repositorio.obtenerUsuarios()
        }


    }

}