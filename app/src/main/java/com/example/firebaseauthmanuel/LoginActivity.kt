package com.example.firebaseauthmanuel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var myemail:EditText
    lateinit var mypassword:EditText
    lateinit var login1:Button
    lateinit var signup:TextView
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        myemail = findViewById(R.id.email1)
        mypassword = findViewById(R.id.password1)
        login1 = findViewById(R.id.login)
        signup = findViewById(R.id.text1)
        auth = FirebaseAuth.getInstance()

        login1.setOnClickListener {
            login()
        }
        signup.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun login(){
        val email = myemail.text.toString()
        val pass = mypassword.text.toString()

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully logged In", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Login has Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}