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
        val tvSalarioBruto = findViewById<TextView>(R.id.tvSalarioBruto)
        val tvSalarioNeto = findViewById<TextView>(R.id.tvSalarioNeto)

        btnCalcular.setOnClickListener {

            val empleado = etEmpleado.text.toString().trim()
            val salarioTexto = etSalario.text.toString().trim()

            // Validar nombre
            if (empleado.isEmpty()) {
                etEmpleado.setError(
                    getString(R.string.error_empleado_vacio)
                )
                return@setOnClickListener
            }

            // Validar salario vacío
            if (salarioTexto.isEmpty()) {
                etSalario.setError(
                    getString(R.string.error_salario_vacio)
                )
                vibrar()
                return@setOnClickListener
            }

            // Convertir salario
            val salario = salarioTexto.toDoubleOrNull()

            // Validar salario positivo
            if (salario == null || salario <= 0) {
                etSalario.setError(
                    getString(R.string.error_salario_invalido)
                )
                vibrar()
                return@setOnClickListener
            }

            // Calcular descuentos
            val afp = salario * 0.0725
            val isss = salario * 0.03
            val renta = calcularRenta(salario)

            // Calcular totales
            val totalDescuentos = afp + isss + renta
            val salarioNeto = salario - totalDescuentos

            // Formato de dos decimales
            val formato = DecimalFormat("0.00")

            val salarioFormateado = formato.format(salario)
            val rentaFormateada = formato.format(renta)
            val afpFormateado = formato.format(afp)
            val isssFormateado = formato.format(isss)
            val totalDescuentosFormateado =
                formato.format(totalDescuentos)
            val salarioNetoFormateado =
                formato.format(salarioNeto)

            // Mostrar detalles de descuentos
            tvResultado.text = getString(
                R.string.resultado_salario,
                empleado,
                rentaFormateada,
                afpFormateado,
                isssFormateado,
                totalDescuentosFormateado
            )

            // Mostrar salario bruto
            tvSalarioBruto.text = getString(
                R.string.salario_bruto,
                salarioFormateado
            )

            // Mostrar salario neto
            tvSalarioNeto.text = getString(
                R.string.salario_neto,
                salarioNetoFormateado
            )
        }

        // Botón regresar
        btnRegresar.setOnClickListener {
            finish()
        }
    }

    // Función para calcular la Renta según los tramos
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

    // Función para hacer vibrar el teléfono
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