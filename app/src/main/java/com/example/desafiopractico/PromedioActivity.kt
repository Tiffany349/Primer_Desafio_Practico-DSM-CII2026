package com.example.desafiopractico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class PromedioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etNota1 = findViewById<EditText>(R.id.etNota1)
        val etNota2 = findViewById<EditText>(R.id.etNota2)
        val etNota3 = findViewById<EditText>(R.id.etNota3)
        val etNota4 = findViewById<EditText>(R.id.etNota4)
        val etNota5 = findViewById<EditText>(R.id.etNota5)

        val btnCalcular = findViewById<Button>(R.id.btnCalcularPromedio)
        val btnRegresar = findViewById<Button>(R.id.btnRegresarPromedio)

        val tvResultado = findViewById<TextView>(R.id.tvResultadoPromedio)

        btnCalcular.setOnClickListener {

            val nombre = etNombre.text.toString().trim()
            val nota1Texto = etNota1.text.toString().trim()
            val nota2Texto = etNota2.text.toString().trim()
            val nota3Texto = etNota3.text.toString().trim()
            val nota4Texto = etNota4.text.toString().trim()
            val nota5Texto = etNota5.text.toString().trim()

            if (nombre.isEmpty() ||
                nota1Texto.isEmpty() ||
                nota2Texto.isEmpty() ||
                nota3Texto.isEmpty() ||
                nota4Texto.isEmpty() ||
                nota5Texto.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    getString(R.string.error_campos_vacios),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val nota1 = nota1Texto.toDouble()
            val nota2 = nota2Texto.toDouble()
            val nota3 = nota3Texto.toDouble()
            val nota4 = nota4Texto.toDouble()
            val nota5 = nota5Texto.toDouble()

            if (nota1 < 0 || nota1 > 10 ||
                nota2 < 0 || nota2 > 10 ||
                nota3 < 0 || nota3 > 10 ||
                nota4 < 0 || nota4 > 10 ||
                nota5 < 0 || nota5 > 10
            ) {
                Toast.makeText(
                    this,
                    getString(R.string.error_notas_rango),
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val promedio = calcularPromedio(
                nota1,
                nota2,
                nota3,
                nota4,
                nota5
            )

            val formato = DecimalFormat("0.00")
            val promedioFormateado = formato.format(promedio)

            val estado = if (promedio >= 6.0) {
                getString(R.string.estado_aprobado)
            } else {
                getString(R.string.estado_reprobado)
            }

            tvResultado.text = getString(
                R.string.resultado_promedio,
                nombre,
                promedioFormateado,
                estado
            )
        }

        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun calcularPromedio(
        nota1: Double,
        nota2: Double,
        nota3: Double,
        nota4: Double,
        nota5: Double
    ): Double {

        val ponderacion = 0.20

        return (nota1 * ponderacion) +
                (nota2 * ponderacion) +
                (nota3 * ponderacion) +
                (nota4 * ponderacion) +
                (nota5 * ponderacion)
    }
}