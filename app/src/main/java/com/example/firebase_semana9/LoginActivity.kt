package com.example.firebase_semana9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth = FirebaseAuth.getInstance()

        val txtEmail: EditText = findViewById(R.id.txtEmail)
        val txtPassword: EditText = findViewById(R.id.txtPassword2)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnRegister: Button = findViewById(R.id.btnRegistrar)

        btnLogin.setOnClickListener {
            val correo = txtEmail.text.toString()
            val clave = txtPassword.text.toString()

            auth.signInWithEmailAndPassword(correo,clave)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Inicio de sesión exitoso"
                                , Snackbar.LENGTH_LONG

                            ).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    }else{
                            Snackbar
                                .make(
                                    findViewById(android.R.id.content)
                                    ,"Credenciales invalidas"
                                    , Snackbar.LENGTH_LONG

                                ).show()
                    }
                }
        }
        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}