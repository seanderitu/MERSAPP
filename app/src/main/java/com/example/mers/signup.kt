package com.example.mers

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mers.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class signup : AppCompatActivity() {
    private lateinit var Name:EditText
    private lateinit var Username:EditText
    private lateinit var Email:EditText
    private lateinit var Phonenumber:EditText
    private lateinit var Password:EditText
    private lateinit var Register:Button
    private lateinit var Alreadyhaveancc:Button
    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        Name=findViewById(R.id.name)
        Username=findViewById(R.id.username)
        Email=findViewById(R.id.email)
        Phonenumber=findViewById(R.id.phonenumber)
        Password=findViewById(R.id.password)
        Register=findViewById(R.id.register2)
        Alreadyhaveancc=findViewById(R.id.alreadyhaveancc)




        Register.setOnClickListener{


            val edtname=Name.text.toString().trim()
            val edtusername=Username.text.toString().trim()
            val edtemail=Email.text.toString().trim()
            val edtphonenumber=Phonenumber.text.toString().trim()
            val edtpassword=Password.text.toString().trim()


            var time_id = System.currentTimeMillis().toString()
            //progressbar

            var progress = ProgressDialog(this)
            progress.setTitle("Saving Data")
            progress.setMessage("Please Wait")

            //validate data\
            if (edtname.isEmpty()|| edtusername.isEmpty()||edtemail.isEmpty()||edtphonenumber.isEmpty()||edtpassword.isEmpty()){
                Toast.makeText(this, "Cannot submit empty field", Toast.LENGTH_SHORT).show()
            }
            else{
                //proceed to upload data to firebase
                var my_child = FirebaseDatabase.getInstance().reference.child("Names/"+time_id)
                var user_data = User(Name,Username,Email,Phonenumber,Password )


                //show progress
                progress.show()

                //save data
                my_child.setValue(user_data).addOnCompleteListener {

                    if (it.isSuccessful){
                        Toast.makeText(this, "Data uploaded succesfully", Toast.LENGTH_SHORT).show()
                        //navigate to another page
                        var gotoview = Intent(this,loginactivity::class.java)
                    }
                    else{
                        Toast.makeText(this, "Failed to upload data", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }

    private fun User(name: EditText?, username: EditText?, email: EditText?, phonenumber: EditText?, password: EditText?) {
    }

}











