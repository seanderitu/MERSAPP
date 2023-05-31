package com.example.mers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginactivity : AppCompatActivity() {
    private lateinit var Email:EditText
    private lateinit var Password:EditText
    private lateinit var ForgotPass:Button
    private lateinit var Login:Button
    private lateinit var Register:Button
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginactivity)

        Email=findViewById(R.id.email)
        Password=findViewById(R.id.password)
        ForgotPass=findViewById(R.id.forgotpass)
        Login=findViewById(R.id.login)
        Register=findViewById(R.id.register)

        auth= FirebaseAuth.getInstance()


        Login.setOnClickListener{


                val edtemail=Email.text.toString().trim()
                val edtpassword=Password.text.toString().trim()

                //validate input
                if (edtemail.isEmpty()||edtpassword.isEmpty()){
                    Toast.makeText(this, "One of the fields is empty", Toast.LENGTH_SHORT).show()
                }
                else{
                    auth.signInWithEmailAndPassword(edtemail,edtpassword).addOnCompleteListener(this){
                        if(it.isSuccessful){
                            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                            val gotomain= Intent(this,UsereProfile::class.java)
                            startActivity(gotomain)
                            finish()

                        }
                        else{
                            Toast.makeText(this, "Login Failed ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        Register.setOnClickListener{

                val gotoreg = Intent(this,signup::class.java)
                startActivity(gotoreg)
                finish()





        }



        }
    }
