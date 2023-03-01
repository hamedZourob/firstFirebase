package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        save.setOnClickListener {
            var name = PersonName.text.toString();
            var id = PersonID.text.toString();
            var age = PersonAge.text.toString();
            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )
            db.collection("person")
                .add(person)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(applicationContext,documentReference.id,Toast.LENGTH_LONG);
                }
                .addOnFailureListener { e ->
                    Toast.makeText(applicationContext,"$e",Toast.LENGTH_LONG);
                }

        }
    }
}