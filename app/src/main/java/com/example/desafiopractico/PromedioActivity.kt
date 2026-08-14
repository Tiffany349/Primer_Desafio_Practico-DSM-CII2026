package com.example.desafiopractico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PromedioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etNota1 = findViewById<EditText>(R.id.etNota1)
        val etNota2 = findViewById<EditText>(R.id.etNota2)
        val etNota3 = findViewById<EditText>(R.id.etNota3)

        val btnCalcular = findViewById<Button>(R.id.btnCalcularPromedio)
        val btnRegresar = findViewById<Button>(R.id.btnRegresarPromedio)

        val tvResultado = findViewById<TextView>(R.id.tvResultadoPromedio)

        btnCalcular.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val nota1Texto = etNota1.text.toString().trim()
            val nota2Texto = etNota2.text.toString().trim()
            val nota3Texto = etNota3.text.toString().trim()

            if (nombre.isEmpty() ||
                nota1Texto.isEmpty() ||
                nota2Texto.isEmpty() ||
                nota3Texto.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val nota1 = nota1Texto.toDouble()
            val nota2 = nota2Texto.toDouble()
            val nota3 = nota3Texto.toDouble()

            val promedio = (nota1 + nota2 + nota3) / 3

            tvResultado.text =
                "$nombre, tu promedio es %.2f".format(promedio)
        }

        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}