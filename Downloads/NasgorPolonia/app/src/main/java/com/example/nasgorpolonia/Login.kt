package com.example.nasgorpolonia

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.nasgorpolonia.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

//setting tombol regis/daftar
        binding.daftar.setOnClickListener {
            val intent = Intent(this, DaftarActivity::class.java)
            startActivity(intent)
        }

        //setting tombol login
        binding.btnMasuk.setOnClickListener {
            val email = binding.editTextTextPersonName.text.toString()
            val kata_sandi = binding.editTextTextPassword.text.toString()

            if (email.isEmpty()) {
                binding.editTextTextPersonName.error = "Email Harus Terisi"
                binding.editTextTextPersonName.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.editTextTextPersonName.error = "Email Tidak Falid"
                binding.editTextTextPersonName.requestFocus()
                return@setOnClickListener
            }

            if (kata_sandi.isEmpty()) {
                binding.editTextTextPassword.error = "Kata Sandi Harus Terisi"
                binding.editTextTextPassword.requestFocus()
                return@setOnClickListener
            }

            if (kata_sandi.length < 8) {
                binding.editTextTextPassword.error = "Kata Sandi Minimal Terdiri 8 Karakter"
                binding.editTextTextPassword.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,kata_sandi)
        }
    }

    private fun LoginFirebase(email: String, kataSandi: String) {

        auth.signInWithEmailAndPassword(email, kataSandi)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login Berhasil! Selamat Datang $email", Toast.LENGTH_LONG).show()
                    val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}