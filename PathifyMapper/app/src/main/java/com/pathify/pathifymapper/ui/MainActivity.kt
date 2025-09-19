package com.pathify.pathifymapper.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pathify.pathifymapper.data.JsonStore
import com.pathify.pathifymapper.databinding.ActivityMainBinding
import com.pathify.pathifymapper.model.AppState

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var store: JsonStore
    private lateinit var state: AppState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        store = JsonStore(this)
        state = store.loadState()

        binding.btnManageBuildings.setOnClickListener {
            startActivity(Intent(this, ManageBuildingsActivity::class.java))
        }
    }

    override fun onPause() {
        super.onPause()
        store.saveState(state)
    }
}
