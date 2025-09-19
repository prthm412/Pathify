package com.pathify.pathifymapper.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pathify.pathifymapper.data.JsonStore
import com.pathify.pathifymapper.databinding.ActivityManageBuildingsBinding
import com.pathify.pathifymapper.model.AppState
import com.pathify.pathifymapper.model.Building

class ManageBuildingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageBuildingsBinding
    private lateinit var store: JsonStore
    private lateinit var state: AppState
    private lateinit var adapter: BuildingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageBuildingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        store = JsonStore(this)
        state = store.loadState()

        adapter = BuildingAdapter(state.buildings) {
            // TODO: open BuildingDetailActivity in next step
        }
        binding.recyclerBuildings.layoutManager = LinearLayoutManager(this)
        binding.recyclerBuildings.adapter = adapter

        binding.btnAddBuilding.setOnClickListener {
            val n = state.buildings.size + 1
            val b = Building(id = "B$n", name = "Building $n")
            state.buildings.add(b)
            adapter.notifyItemInserted(state.buildings.lastIndex)
        }
    }

    override fun onPause() {
        super.onPause()
        store.saveState(state)
    }
}
