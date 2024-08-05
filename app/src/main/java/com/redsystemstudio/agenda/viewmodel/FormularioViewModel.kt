package com.redsystemstudio.agenda.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redsystemstudio.agenda.config.Constantes
import com.redsystemstudio.agenda.config.PersonalApp.Companion.db
import com.redsystemstudio.agenda.models.Personal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormularioViewModel : ViewModel() {
    var id = MutableLiveData<Long>()
    var nombre = MutableLiveData<String>()
    var apellidos = MutableLiveData<String>()
    var telefono = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var edad = MutableLiveData<Int>()
    var operacion: String = Constantes.OPERACION_INSERTAR
    var operacionExitosa = MutableLiveData<Boolean>()

    init {
        edad.value = 18
    }

    fun guardarUsuario() {
        val mPersonal = Personal(
            id.value?.toInt() ?: 0,
            nombre.value!!,
            apellidos.value!!,
            email.value!!,
            telefono.value!!,
            edad.value!!
        )
        when (operacion) {
            Constantes.OPERACION_INSERTAR -> {
                viewModelScope.launch {
                    val result: List<Long> = withContext(Dispatchers.IO) {
                        db.personalDao().insert(arrayListOf(mPersonal))
                    }
                    operacionExitosa.value = result.isNotEmpty()
                }
            }

            Constantes.OPERACION_EDITAR -> {
                viewModelScope.launch {
                    val result: Int = withContext(Dispatchers.IO) {
                        db.personalDao().update(mPersonal)
                    }
                    operacionExitosa.value = result > 0
                }
            }
        }
    }

    fun eliminarUsuario() {
        val mPersonal = Personal(
            id.value?.toInt() ?: 0,
            nombre.value!!,
            apellidos.value!!,
            email.value!!,
            telefono.value!!,
            edad.value!!
        )
        viewModelScope.launch {
            val result: Int = withContext(Dispatchers.IO) {
                db.personalDao().delete(mPersonal)
            }
            operacionExitosa.value = result > 0
        }
    }
}
