package com.example.firebase_semana9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val txtFullName: EditText = findViewById(R.id.txtFullName)
        val txtCountry: EditText = findViewById(R.id.txtCountry)
        val txtEmail: EditText = findViewById(R.id.txtEmailR)
        val txtPassword: EditText = findViewById(R.id.txtContaseñaR)
        val btnSave: Button = findViewById(R.id.btnSaveRegister)
        val auth=FirebaseAuth.getInstance()
        val db=FirebaseFirestore.getInstance()
        val collectionRef=db.collection("users")

        btnSave.setOnClickListener {
            val correo = txtEmail.text.toString()
            val clave = txtPassword.text.toString()
            val nombreCompleto = txtFullName.text.toString()
            val pais = txtCountry.text.toString()

            auth.createUserWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        //Se registra
                        val user: FirebaseUser? = auth.currentUser
                        val uid = user?.uid

                        val userModel= UserModel(correo, clave, nombreCompleto, pais, uid.toString())
                        collectionRef.add(userModel)
                            .addOnCompleteListener{

                            }.addOnFailureListener { error->
                                Snackbar
                                    .make(
                                        findViewById(android.R.id.content)
                                        ,"Ocurrio un error en el registro "
                                        , Snackbar.LENGTH_LONG

                                    ).show()
                            }
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Registro exitoso"
                                , Snackbar.LENGTH_LONG

                            ).show()
                    }else{
                        Snackbar
                            .make(
                                findViewById(android.R.id.content)
                                ,"Ocurrio un error al registrarse"
                                , Snackbar.LENGTH_LONG

                            ).show()
                    }
                }

        }


    }
}