package com.pathify.pathifymapper.ui

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import androidx.activity.result.contract.ActivityResultContracts
import com.pathify.pathifymapper.data.JsonStore
import com.pathify.pathifymapper.databinding.ActivityMapImportBinding
import com.pathify.pathifymapper.model.AppState
import com.pathify.pathifymapper.model.Building
import com.pathify.pathifymapper.model.FloorRef

class MapImportActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BUILDING_ID = "building_id"
        const val EXTRA_FLOOR_ID = "floor_id"
    }

    private lateinit var binding: ActivityMapImportBinding
    private lateinit var store: JsonStore
    private lateinit var state: AppState

    private var building: Building? = null
    private var floor: FloorRef? = null
    private var pickedUri: Uri? = null

    // SAF picker for images
    private val pickImage = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            } catch (_: SecurityException) {/* may already be persisted */}

                pickedUri = uri
                binding.imgPreview.setImageURI(uri)
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMapImportBinding.inflate(layoutInflater)
            setContentView(binding.root)

            store = JsonStore(this)
            state = store.loadState()

            val bId = intent.getStringExtra(EXTRA_BUILDING_ID)
            val fId = intent.getStringExtra(EXTRA_FLOOR_ID)

            building = state.buildings.firstOrNull { it.id == bId }
            floor = building?.floors?.firstOrNull { it.id == fId }

            binding.tvHeader.text = floor?.let { "Import Map • ${it.displayName} (${it.id})" } ?: "Import Map"

            // show any previously saved image
            floor?.imageUri?.let { saved ->
                pickedUri = Uri.parse(saved)
                binding.imgPreview.setImageURI(pickedUri)
            }

            binding.btnPickImage.setOnClickListener {
                // Filter to images; let user pick from any provider
                pickImage.launch(arrayOf("image/png", "image/jped", "image/webp"))
            }

            binding.btnSave.setOnClickListener {
                val uri = pickedUri ?: return@setOnClickListener
                // normalize tree/document URI if needed (optional)
                // ensure we have read permission
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                floor?.imageUri=uri.toString()
                store.saveState(state)
                finish()
            }
        }

}