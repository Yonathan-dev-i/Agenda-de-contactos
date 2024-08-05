package com.redsystemstudio.agenda.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.redsystemstudio.agenda.MainActivity
import com.redsystemstudio.agenda.config.Constantes
import com.redsystemstudio.agenda.config.PersonalApp.Companion.db
import com.redsystemstudio.agenda.databinding.ActivityFormularioBinding
import com.redsystemstudio.agenda.viewmodel.FormularioViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormularioActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormularioBinding
    lateinit var viewModel: FormularioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FormularioViewModel::class.java)
        viewModel.operacion = intent.getStringExtra(Constantes.OPERACION_KEY)!!

        if (viewModel.operacion == Constantes.OPERACION_EDITAR) {
            val id = intent.getLongExtra("ID", 0L)
            viewModel.id.value = id
            // Load contact details for editing
            loadContactDetails(id)
        } else {
            binding.btnEliminar.visibility = View.GONE // Hide delete button if inserting
        }

        binding.modelo = viewModel
        binding.lifecycleOwner = this

        viewModel.operacionExitosa.observe(this, Observer {
            if (it) {
                mostrarMensaje("Operación Exitosa")
                irAlInicio()
            } else {
                mostrarMensaje("Operación Fallida")
            }
        })
    }

    private fun loadContactDetails(id: Long) {
        // Verifica que la base de datos esté correctamente inicializada
        val dao = db.personalDao()

        // Usa corutinas para realizar operaciones en el hilo de fondo
        viewModel.viewModelScope.launch {
            val contact = withContext(Dispatchers.IO) {
                dao.getById(id.toInt())
            }

            // Actualiza los LiveData en el hilo principal
            runOnUiThread {
                contact?.let {
                    viewModel.nombre.value = it.nombre
                    viewModel.apellidos.value = it.apellidos
                    viewModel.email.value = it.mail
                    viewModel.telefono.value = it.telefono
                    viewModel.edad.value = it.edad
                }
            }
        }
    }
    private fun mostrarMensaje(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
    }

    private fun irAlInicio() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
