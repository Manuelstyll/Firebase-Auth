package com.example.firebaseauthmanuel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var myemail:EditText
    lateinit var mypassword:EditText
    lateinit var mycpassword:EditText
    lateinit var signup:Button
    lateinit var mylogin:TextView
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myemail = findViewById(R.id.email)
        mypassword = findViewById(R.id.password)
        mycpassword = findViewById(R.id.cpassword)
        signup = findViewById(R.id.signup)
        mylogin = findViewById(R.id.text)
        auth = Firebase.auth

        mylogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        signup.setOnClickListener {
            SignupUser()
        }
    }

    private fun SignupUser(){
        val email = myemail.text.toString()
        val pass = mypassword.text.toString()
        val cpass = mycpassword.text.toString()
        if (email.isBlank()||pass.isBlank()||cpass.isBlank()){
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Signed up Successfully", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Failed to create", Toast.LENGTH_SHORT).show()
            }
        }
    }
}