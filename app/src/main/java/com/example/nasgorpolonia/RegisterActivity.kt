package com.example.nasgorpolonia

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvLogin: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        etAddress = findViewById(R.id.etAddress)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        tvLogin = findViewById(R.id.tvLogin)

        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        btnRegister.setOnClickListener {
            registerUser()
        }

        tvLogin.setOnClickListener {
            // Pindah ke LoginActivity
            finish() // Menutup aktivitas ini dan kembali ke aktivitas sebelumnya
        }
    }

    private fun registerUser() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val address = etAddress.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        if (TextUtils.isEmpty(name)) {
            etName.error = "Nama tidak boleh kosong"
            return
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.error = "Email tidak boleh kosong"
            return
        }

        if (TextUtils.isEmpty(phone)) {
            etPhone.error = "Nomor telepon tidak boleh kosong"
            return
        }

        if (TextUtils.isEmpty(address)) {
            etAddress.error = "Alamat tidak boleh kosong"
            return
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.error = "Kata sandi tidak boleh kosong"
            return
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.error = "Konfirmasi kata sandi tidak boleh kosong"
            return
        }

        if (password != confirmPassword) {
            etConfirmPassword.error = "Kata sandi tidak cocok"
            return
        }

        // Mendaftarkan pengguna dengan Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Simpan data pengguna ke Realtime Database
                    val userId = mAuth.currentUser?.uid
                    val user = User(name, email, phone, address)

                    if (userId != null) {
                        mDatabase.reference.child("users").child(userId).setValue(user)
                            .addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
                                    Toast.makeText(this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show()
                                    // Navigasi ke LoginActivity
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this, "Gagal menyimpan data pengguna", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                } else {
                    Toast.makeText(this, "Pendaftaran gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    data class User(val name: String, val email: String, val phone: String, val address: String)
}
