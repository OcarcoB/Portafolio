package com.example.portafolio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.portafolio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object{
        lateinit var auth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signIn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        binding.signOut.setOnClickListener {
            auth.signOut()
            binding.userDetails.text = updateData()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.userDetails.text = updateData()
    }

    private fun updateData(): String{
        return "Email : ${auth.currentUser?.email}"
    }
}