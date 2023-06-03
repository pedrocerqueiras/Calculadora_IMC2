package com.example.meuprimeiroprojeto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalcular: Button = findViewById(R.id.btnCalcular)
        val edtPeso: EditText = findViewById(R.id.edtPeso)
        val edtAltura: EditText = findViewById(R.id.edtAltura)

        edtAltura.addTextChangedListener(object : TextWatcher {
            private var isEditing: Boolean = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nada a fazer aqui
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Nada a fazer aqui
            }

            override fun afterTextChanged(s: Editable?) {
                if (isEditing) return

                isEditing = true

                // Remove qualquer ponto existente
                val textWithoutDots = s.toString().replace(".", "")

                // Verifica se há pelo menos um número antes do ponto
                if (textWithoutDots.isNotEmpty()) {
                    val intValue = textWithoutDots.toInt()
                    val formattedValue = intValue / 100.toFloat()


                    // Atualiza o texto com o ponto no lugar adequado
                    edtAltura.setText(String.format("%.2f", formattedValue))
                    edtAltura.setSelection(edtAltura.text.length)
                }

                isEditing = false
            }
        })

        btnCalcular.setOnClickListener {

            val alturaStr = edtAltura.text.toString()
            val pesoStr = edtPeso.text.toString()

            if (alturaStr.isNotEmpty() && pesoStr.isNotEmpty()) {

                val altura: Float = alturaStr.toFloat()
                val peso: Float = pesoStr.toFloat()
                val alturaFinal: Float = altura * altura
                val result: Float = peso / alturaFinal


                val intent = Intent(this, ResultActivity::class.java)
                    .apply {
                        putExtra("EXTRA_RESULT", result)
                    }

                startActivity(intent)

            } else {

                Toast.makeText( this,"Preencher todos os campos", Toast.LENGTH_LONG).show()
                
            }

        }

    }
}