package com.example.desafiopractico

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class CalculadoraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        val etNumero1 = findViewById<EditText>(R.id.etNumero1)
        val etNumero2 = findViewById<EditText>(R.id.etNumero2)

        val tvResultado =
            findViewById<TextView>(R.id.tvResultadoCalculadora)

        val btnSuma = findViewById<Button>(R.id.btnSuma)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnMultiplicacion =
            findViewById<Button>(R.id.btnMultiplicacion)
        val btnDivision = findViewById<Button>(R.id.btnDivision)
        val btnExponente = findViewById<Button>(R.id.btnExponente)
        val btnRaiz = findViewById<Button>(R.id.btnRaiz)
        val btnRegresar =
            findViewById<Button>(R.id.btnRegresarCalculadora)

        btnSuma.setOnClickListener {
            calcularOperacion(etNumero1, etNumero2, tvResultado, "+")
        }

        btnResta.setOnClickListener {
            calcularOperacion(etNumero1, etNumero2, tvResultado, "-")
        }

        btnMultiplicacion.setOnClickListener {
            calcularOperacion(etNumero1, etNumero2, tvResultado, "*")
        }

        btnDivision.setOnClickListener {
            calcularOperacion(etNumero1, etNumero2, tvResultado, "/")
        }

        btnExponente.setOnClickListener {
            calcularOperacion(etNumero1, etNumero2, tvResultado, "^")
        }

        btnRaiz.setOnClickListener {
            calcularRaiz(etNumero1, tvResultado)
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    private fun calcularOperacion(
        etNumero1: EditText,
        etNumero2: EditText,
        tvResultado: TextView,
        operacion: String
    ) {

        val numero1Texto = etNumero1.text.toString().trim()
        val numero2Texto = etNumero2.text.toString().trim()

        if (numero1Texto.isEmpty() || numero2Texto.isEmpty()) {
            Toast.makeText(
                this,
                getString(R.string.error_campos_calculadora),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val numero1 = numero1Texto.toDoubleOrNull()
        val numero2 = numero2Texto.toDoubleOrNull()

        if (numero1 == null || numero2 == null) {
            Toast.makeText(
                this,
                getString(R.string.error_numero_invalido),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (operacion == "/" && numero2 == 0.0) {
            Toast.makeText(
                this,
                getString(R.string.error_division_cero),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val resultado = when (operacion) {
            "+" -> numero1 + numero2
            "-" -> numero1 - numero2
            "*" -> numero1 * numero2
            "/" -> numero1 / numero2
            "^" -> numero1.pow(numero2)
            else -> 0.0
        }

        tvResultado.text = resultado.toString()
    }

    private fun calcularRaiz(
        etNumero1: EditText,
        tvResultado: TextView
    ) {

        val numeroTexto = etNumero1.text.toString().trim()

        if (numeroTexto.isEmpty()) {
            Toast.makeText(
                this,
                getString(R.string.error_numero_invalido),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val numero = numeroTexto.toDoubleOrNull()

        if (numero == null || numero < 0) {
            Toast.makeText(
                this,
                getString(R.string.error_numero_invalido),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val resultado = sqrt(numero)

        tvResultado.text = resultado.toString()
    }
}