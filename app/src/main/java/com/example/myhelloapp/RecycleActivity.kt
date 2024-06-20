package com.example.myhelloapp


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myhelloapp.databinding.ActivityMainBinding

class RecycleActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val users = listOf(
            User(1,"Alice", 25),
            User(2,"Bob", 30),
            User(3,"Charlie", 28),
            User(4,"David", 35),
            User(5,"Eve", 22),
            User(6,"Frank", 33),
            User(7,"Grace", 27),
            User(8,"Harry", 40),
            User(9,"Isabel", 29),
            User(10,"Jack", 31),
        )

        binding.recycleView.layoutManager = LinearLayoutManager(this)

        val adapter = UserAdapter { view, position ->
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = users[position]
                    val intent = Intent(view.context, UserActivity::class.java)
                    intent.putExtra("User Name", clickedItem.name)
                    intent.putExtra("User Age", clickedItem.age)
                    view.context.startActivity(intent)
                }
        }

        binding.recycleView.adapter = adapter
        adapter.submitList(users.toMutableList())

        binding.addBT.setOnClickListener{
            adapter.addUsers(User(100, "New User", 25))
            Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
        }
    }
}