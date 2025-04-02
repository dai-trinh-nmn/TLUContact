package com.example.tlucontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tlucontact.databinding.ItemStaffBinding

//Adapter cho RecyclerView hien thi danh sach cac nhan vien
class StaffAdapter(
    private var staffList: List<Staff>,
    private val onItemClick: (Staff) -> Unit
): RecyclerView.Adapter<StaffAdapter.StaffViewHolder>() {

    //ViewHolder chua cac view cua item
    class StaffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvStaffName)
        val position: TextView = itemView.findViewById(R.id.tvStaffPosition)
        val phone: TextView = itemView.findViewById(R.id.tvStaffPhone)
        val cardView: CardView = itemView.findViewById(R.id.cardViewStaff)
    }

    //Tao ViewHolder moi khi RecyclerView can
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_staff, parent, false)
        return StaffViewHolder(view)
    }

    //Gan du lieu cho ViewHolder
    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        val staff = staffList[position]
        holder.name.text = "${staff.name}"
        holder.position.text = "${staff.position}"
        holder.phone.text = "${staff.phone}"

        holder.cardView.setOnClickListener {
            onItemClick(staff)
        }
    }

    override fun getItemCount(): Int = staffList.size

    //Cap nhat danh sach hien thi va thong bao cho adapter
    fun updateList(filteredList: List<Staff>) {
        staffList = filteredList
        notifyDataSetChanged()
    }
}
