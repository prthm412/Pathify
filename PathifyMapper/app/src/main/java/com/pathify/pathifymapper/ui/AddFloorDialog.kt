package com.pathify.pathifymapper.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout

class AddFloorDialog(
    ctx: Context,
    private val onAdd: (id: String, displayName: String) -> Unit
) {

    private val dialog = AlertDialog.Builder(ctx).create()

    fun show() {
        val ctx = dialog.context
        val container = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 32, 48, 16)
        }
        val etId = EditText(ctx).apply { hint = "Floor ID e.g. G, 1, 2" }
        val etName = EditText(ctx).apply { hint = "Display name e.g. Ground Floor" }
        container.addView(etId)
        container.addView(etName)

        dialog.setTitle("Add Floor")
        dialog.setView(container)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Add") { _, _ ->
            val id = etId.text.toString().trim().ifEmpty { "F${System.currentTimeMillis()%1000}" }
            val name = etName.text.toString().trim().ifEmpty { id }
            onAdd(id, name)
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel") { d, _ -> d.dismiss() }
        dialog.show()
    }
}
