package com.example.mers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UsereProfile : AppCompatActivity() {
    private lateinit var Rent:Button
    private lateinit var Rentout:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usere_profile)
        Rent=findViewById(R.id.btn_rent)
        Rentout=findViewById(R.id.btn_rentout)

        Rent.setOnClickListener{
            val gotodash = Intent(this,Dashboard::class.java)
            startActivity(gotodash)
            finish()
        }

    }
}