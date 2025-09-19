package com.pathify.pathifymapper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pathify.pathifymapper.databinding.ItemBuildingBinding
import com.pathify.pathifymapper.model.Building

class BuildingAdapter(
    private val items: List<Building>,
    private val onClick: (Building) -> Unit
) : RecyclerView.Adapter<BuildingAdapter.VH>() {

    class VH(val vb: ItemBuildingBinding) : RecyclerView.ViewHolder(vb.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val vb = ItemBuildingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(vb)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val b = items[position]
        holder.vb.tvName.text = b.name
        holder.vb.tvId.text = b.id
        holder.itemView.setOnClickListener { onClick(b) }
    }

    override fun getItemCount(): Int = items.size
}
