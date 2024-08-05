package com.redsystemstudio.agenda

import PersonalAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.redsystemstudio.agenda.databinding.ActivityMainBinding
import com.redsystemstudio.agenda.viewmodel.MainViewModel
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.redsystemstudio.agenda.config.Constantes
import com.redsystemstudio.agenda.ui.FormularioActivity

class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO ENLAZAR EL MODELO COM LIVE DATA
        viewModel = ViewModelProvider(this).get()
        viewModel.iniciar()

        binding.miRecycler.apply {
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.personalList.observe(this, Observer {
            binding.miRecycler.adapter = PersonalAdapter(it)
        })

        binding.btnAbrirFormulario.setOnClickListener {
            val intent = Intent(this,FormularioActivity::class.java)
            intent.putExtra(Constantes.OPERACION_KEY,Constantes.OPERACION_INSERTAR)
            startActivity(intent)

        }

    }
}