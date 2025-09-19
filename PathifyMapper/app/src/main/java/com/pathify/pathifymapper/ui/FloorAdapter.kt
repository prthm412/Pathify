package com.pathify.pathifymapper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pathify.pathifymapper.databinding.ItemFloorBinding
import com.pathify.pathifymapper.model.FloorRef

class FloorAdapter(
    private val items: MutableList<FloorRef>,
    private val onClick: (FloorRef) -> Unit
) : RecyclerView.Adapter<FloorAdapter.VH>() {

    class VH(val vb: ItemFloorBinding) : RecyclerView.ViewHolder(vb.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val vb = ItemFloorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(vb)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val f = items[position]
        holder.vb.tvFloorName.text = f.displayName
        holder.vb.tvFloorId.text = f.id
        holder.itemView.setOnClickListener { onClick(f) }
    }

    override fun getItemCount(): Int = items.size
}
