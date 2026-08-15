package com.example.desafiopractico

import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SalarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        val etEmpleado = findViewById<EditText>(R.id.etEmpleado)
        val etSalario = findViewById<EditText>(R.id.etSalario)

        val btnCalcular = findViewById<Button>(R.id.btnCalcularSalario)
        val btnRegresar = findViewById<Button>(R.id.btnRegresarSalario)

        val tvResultado = findViewById<TextView>(R.id.tvResultadoSalario)

        btnCalcular.setOnClickListener {

            val empleado = etEmpleado.text.toString().trim()
            val salarioTexto = etSalario.text.toString().trim()

            if (empleado.isEmpty()) {
                etEmpleado.setError(
                    getString(R.string.error_empleado_vacio)
                )
                return@setOnClickListener
            }

            if (salarioTexto.isEmpty()) {
                etSalario.setError(
                    getString(R.string.error_salario_vacio)
                )
                vibrar()
                return@setOnClickListener
            }

            val salario = salarioTexto.toDoubleOrNull()

            if (salario == null || salario <= 0) {
                etSalario.setError(
                    getString(R.string.error_salario_invalido)
                )
                vibrar()
                return@setOnClickListener
            }
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun vibrar() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {

            val vibratorManager =
                getSystemService(VibratorManager::class.java)

            val vibrator = vibratorManager.defaultVibrator

            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    300,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )

        } else {

            val vibrator =
                getSystemService(Vibrator::class.java)

            @Suppress("DEPRECATION")
            vibrator.vibrate(300)
        }
    }
}