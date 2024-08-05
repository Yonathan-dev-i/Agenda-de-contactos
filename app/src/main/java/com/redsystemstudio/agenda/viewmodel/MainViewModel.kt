package com.redsystemstudio.agenda.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redsystemstudio.agenda.config.PersonalApp.Companion.db
import com.redsystemstudio.agenda.models.Personal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    val personalList = MutableLiveData<List<Personal>>()
    val parametroBusqueda = MutableLiveData<String>()

    fun iniciar() {
        viewModelScope.launch {
            personalList.value = withContext(Dispatchers.IO) {
                // Inserta datos en la base de datos
                /*db.personalDao().insert(
                    arrayListOf(
                        Personal(0, "Jose", "Perez", "Jose@gmail.com", "984202454", 25),
                        Personal(0, "Juan", "Medina", "Juan@gmail.com", "982567454", 30)
                    )
                )*/
                // Obtiene todos los datos de la base de datos
                db.personalDao().getAll()
            }
        }
    }
}

