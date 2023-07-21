package com.example.ejercicio_individual_14_modulo_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ejercicio_individual_14_modulo_5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var saldo: Double = 0.00 // Variable para mantener el saldo actual
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Lógica para manejar el botón "OK"
        binding.btnOk.setOnClickListener {
        val selectedRadioButtonId = binding.rgTransaction.checkedRadioButtonId
        val etMonto = binding.etMonto
        val etSaldo = binding.etSaldo

            if (etMonto.text.isEmpty() && (binding.rbIngresar.isChecked || binding.rbRetirar.isChecked)){
                showToast("Debe ingresar un monto válido")
            }

            when (selectedRadioButtonId) {
                R.id.rbIngresar -> {
                    val monto = etMonto.text.toString().toDoubleOrNull()
                    if (monto != null) {
                        saldo += monto
                        etSaldo.setText(saldo.toString())
                        binding.etSaldo.visibility = View.VISIBLE
                        showToast("Monto ingresado correctamente.")
                    }
                }

                R.id.rbRetirar -> {
                    val monto = etMonto.text.toString().toDoubleOrNull()
                    if (monto != null) {
                        if (monto <= saldo) {
                            saldo -= monto
                            etSaldo.setText(saldo.toString())
                            binding.etSaldo.visibility = View.VISIBLE
                            showToast("Monto retirado correctamente.")
                        } else {
                            showToast("Monto de retiro supera el saldo. Realizar nuevamente")
                            binding.etSaldo.visibility = View.VISIBLE
                        }
                    }
                }
                R.id.rbSaldo -> {
                    binding.etSaldo.visibility = View.VISIBLE
                    binding.etSaldo.setText(saldo.toString())
                }
                R.id.rbSalir -> {
                    finish()
                }
            }
        etMonto.setText("")
        }
       binding.rgTransaction.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbIngresar -> {
                    // Ocultar el EditText etSaldo
                    binding.etSaldo.visibility = View.GONE
                }

                R.id.rbRetirar -> {
                    // Ocultar el EditText etSaldo
                    binding.etSaldo.visibility = View.GONE
                }

                R.id.rbSaldo -> {
                    binding.etMonto.setText("")
                    // Mostrar el EditText etSaldo
                    binding.etSaldo.visibility = View.GONE
                    binding.etSaldo.setText(saldo.toString())
                }
            }
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
