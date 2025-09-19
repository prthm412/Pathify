package com.pathify.pathifymapper.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pathify.pathifymapper.data.JsonStore
import com.pathify.pathifymapper.databinding.ActivityBuildingDetailBinding
import com.pathify.pathifymapper.model.AppState
import com.pathify.pathifymapper.model.Building
import com.pathify.pathifymapper.model.FloorRef

class BuildingDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_BUILDING_ID = "building_id"
    }

    private lateinit var binding: ActivityBuildingDetailBinding
    private lateinit var store: JsonStore
    private lateinit var state: AppState
    private var building: Building? = null
    private lateinit var adapter: FloorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        store = JsonStore(this)
        state = store.loadState()

        val bId = intent.getStringExtra(EXTRA_BUILDING_ID)
        building = state.buildings.firstOrNull { it.id == bId }

        binding.tvTitle.text = building?.let { "${it.name}  •  ${it.id}" } ?: "Building"

        adapter = FloorAdapter(building?.floors ?: mutableListOf()) {
            // TODO: open MapImportActivity in the next step
        }
        binding.recyclerFloors.layoutManager = LinearLayoutManager(this)
        binding.recyclerFloors.adapter = adapter

        binding.btnAddFloor.setOnClickListener {
            AddFloorDialog(this) { floorId, displayName ->
                val ref = FloorRef(id = floorId, displayName = displayName)
                building?.floors?.add(ref)
                adapter.notifyItemInserted((building?.floors?.size ?: 1) - 1)
                store.saveState(state)
            }.show()
        }
    }

    override fun onPause() {
        super.onPause()
        store.saveState(state)
    }
}