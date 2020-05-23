package com.ziesapp.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editLebar: EditText
    private lateinit var editTinggi: EditText
    private lateinit var editPanjang: EditText
    private lateinit var btnHitung: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editLebar = findViewById(R.id.edit_lebar)
        editTinggi = findViewById(R.id.edit_tinggi)
        editPanjang = findViewById(R.id.edit_panjang)
        btnHitung = findViewById(R.id.btnHitung)
        tvHasil = findViewById(R.id.tv_hasil)

        btnHitung.setOnClickListener(this)

        if (savedInstanceState != null) {
            val hasil = savedInstanceState.getString(STATE_RESULT) as String
            tvHasil.text = hasil
        }

    }

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvHasil.text.toString())
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnHitung) {
            val inputPanjang = editPanjang.text.toString().trim()
            val inputLebar = editLebar.text.toString().trim()
            val inputTinggi = editTinggi.text.toString().trim()

            var apakahKosong = false
            var apakahValid = false

            if (inputPanjang.isEmpty()) {
                apakahKosong = true
                editPanjang.error = "Field ini tidak boleh kosong"
            }

            if (inputLebar.isEmpty()) {
                apakahKosong = true
                editLebar.error = "Field ini tidak boleh kosong"
            }

            if (inputTinggi.isEmpty()) {
                apakahKosong = true
                editTinggi.error = "Field ini tidak boleh kosong"
            }

            val panjang = toDouble(inputPanjang)
            val lebar = toDouble(inputLebar)
            val tinggi = toDouble(inputTinggi)

            if (panjang == null) {
                apakahValid = true
                editPanjang.error = "Field ini harus dengan format yang benar"
            }

            if (lebar == null) {
                apakahValid = true
                editLebar.error = "Field ini harus dengan format yang benar"
            }

            if (tinggi == null) {
                apakahValid = true
                editTinggi.error = "Field ini harus dengan format yang benar"
            }

            if (!apakahKosong && !apakahValid) {
                val volume = panjang as Double * lebar as Double * tinggi as Double
                tvHasil.text = volume.toString()
            }
        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }


}
