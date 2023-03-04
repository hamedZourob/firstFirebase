package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.widget.Toast
import android.os.Bundle
import android.view.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
val db = Firebase.firestore
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progBarTwo.visibility = View.GONE
        btn3.setOnClickListener {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
        }
        btn.setOnClickListener {
            progBarTwo.visibility = View.VISIBLE
            var name = number.text.toString()
            var address = address.text.toString()
            var number = fullname.text.toString()

            if(name.isEmpty() || number.isEmpty() || address.isEmpty() ){
                progBarTwo.visibility = View.GONE
                Toast.makeText(this, "The field is empty ", Toast.LENGTH_SHORT).show()
            }else{
                val person = hashMapOf(
                    "name" to "$name",
                    "number" to "$number",
                    "address" to "$address"
                )
                db.collection("person").add(person).addOnSuccessListener {e ->
                    progBarTwo.visibility = View.GONE
                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()
                    this.fullname.text.clear()
                    this.address.text.clear()
                    this.number.text.clear()
                }.addOnFailureListener { e->
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }
}