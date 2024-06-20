package com.example.myhelloapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myhelloapp.databinding.LoginPageBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { LoginPageBinding.inflate(LayoutInflater.from(this)) }
    private lateinit var inputText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.forgot.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder
                .setMessage("Did you forgot?")
                .setTitle("Forgot Password?")
                .setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        inputText = binding.inputName
        binding.signupBT.setOnClickListener {
            val result = inputText.text.toString()
            val intent = Intent()
            intent.putExtra("result", result)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, HelloPageBinding::class.java))
//    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Activity 2: On Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Activity 2: On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Activity 2: On Stop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Activity 2: On Restart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Activity 2: On Destroy")
    }
}


