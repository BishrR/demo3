package com.example.myhelloapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myhelloapp.databinding.HelloPageBinding
import com.google.gson.Gson


const val TAG = "bisher_tag"

class MainActivity : AppCompatActivity() {


    private val binding by lazy { HelloPageBinding.inflate(LayoutInflater.from(this)) }
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.facebook.setOnClickListener {
            val intent = Intent(this, RecycleActivity::class.java)
            startActivity(intent)
        }

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data: Intent? = result.data
                    binding.hello.text = data?.getStringExtra("result")
                }
            }
        binding.loginBT.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            launcher.launch(intent)
        }

    }

//        binding.loginBT.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivityForResult(intent, 0)
//
//        }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                // Get the result from intent
//                val result = intent.getStringExtra("result")
//                // set the result to the text view
//                binding.hello.text = result
//            }
//        }
//    }

}

