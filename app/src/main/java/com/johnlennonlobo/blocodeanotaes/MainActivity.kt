package com.johnlennonlobo.blocodeanotaes

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.johnlennonlobo.blocodeanotaes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var editTextAnnotation: EditText
    private lateinit var preferences: AnnotationPreferences
    private lateinit var textAnnotation: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextAnnotation = findViewById(R.id.editTextAnnotation)
        preferences = AnnotationPreferences(applicationContext)

        var retrieveAnnotation = preferences.retrieveAnnotation()
        if(retrieveAnnotation != ""){
            editTextAnnotation.setText(retrieveAnnotation)
        }

        binding.fab.setOnClickListener { view ->
            textAnnotation = editTextAnnotation.text.toString()
            if (textAnnotation == "") {
                showMessage(view,"Preencha a anotação!")

            } else {
                preferences.saveAnnotation(textAnnotation)
                showMessage(view,"Anotação salva com sucesso!")
            }
        }
    }

    private fun showMessage(view: View, message:String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.brown_200))
        snackbar.show()
    }

    override fun onBackPressed() {
        textAnnotation = editTextAnnotation.text.toString()
            AlertDialog.Builder(this)
                .setTitle("Salva Anotação")
                .setMessage("Deseja salvar a anotação antes de sair?")

                .setPositiveButton("SIM") { _, _ ->
                    preferences.saveAnnotation(textAnnotation)
                    super.onBackPressed()
                }

                .setNegativeButton("NÃO") { _, _ ->
                    super.onBackPressed()
                }
                .create()
                .show()

    }
}