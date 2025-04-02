package com.example.tlucontactfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var errorTextView: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        errorTextView = findViewById(R.id.errorTextView)
        auth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            registerUser()
        }

        loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun registerUser() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (isValidStudentEmail(email)) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                    } else {
                        errorTextView.text = "Đăng ký thất bại: ${task.exception?.message}"
                    }
                }
        } else {
            errorTextView.text = "Email không đúng định dạng (@e.tlu.edu.vn)"
        }
    }

    private fun loginUser() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                    sessionManager.saveEmail(email)
                    sessionManager.saveLoginStatus(true)

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    errorTextView.text = "Đăng nhập thất bại: ${task.exception?.message}"
                }
            }
    }

    private fun isValidStudentEmail(email: String): Boolean {
        return email.endsWith("@e.tlu.edu.vn")
    }
}