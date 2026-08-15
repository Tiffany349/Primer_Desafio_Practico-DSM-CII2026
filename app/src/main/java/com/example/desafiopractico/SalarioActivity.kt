package com.example.desafiopractico

import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

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

            val afp = salario * 0.0725
            val isss = salario * 0.03
            val renta = calcularRenta(salario)

            val totalDescuentos = afp + isss + renta
            val salarioNeto = salario - totalDescuentos

            val formato = DecimalFormat("0.00")

            tvResultado.text = getString(
                R.string.resultado_salario,
                empleado,
                formato.format(salario),
                formato.format(renta),
                formato.format(afp),
                formato.format(isss),
                formato.format(totalDescuentos),
                formato.format(salarioNeto)
            )
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun calcularRenta(salario: Double): Double {

        return when {
            salario <= 472.00 -> {
                0.0
            }

            salario <= 895.24 -> {
                ((salario - 472.00) * 0.10) + 17.67
            }

            salario <= 2038.10 -> {
                ((salario - 895.24) * 0.20) + 60.00
            }

            else -> {
                ((salario - 2038.10) * 0.30) + 288.57
            }
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