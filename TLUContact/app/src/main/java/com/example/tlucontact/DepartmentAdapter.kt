package com.example.tlucontact

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

//Adapter cho RecuclerView hien thi danh sach cac phong ban
class DepartmentAdapter(
    private var departmentList: List<Department>,
    private val onItemClick: (Department) -> Unit
) : RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder>() {

    //ViewHolder chua cac view cua item
    class DepartmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvDepartmentName)
        val phone: TextView = itemView.findViewById(R.id.tvDepartmentPhone)
        val address: TextView = itemView.findViewById(R.id.tvDepartmentAddress)
        val cardView: CardView = itemView.findViewById(R.id.cardViewDepartment)
    }

    //Tao ViewHolder moi khi RecyclerView can
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_department, parent, false)
        return DepartmentViewHolder(view)
    }

    //Gan du lieu cho ViewHolder
    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        val department = departmentList[position]
        holder.name.text = "${department.name}"
        holder.phone.text = "${department.phone}"
        holder.address.text = "${department.address}"

        holder.cardView.setOnClickListener {
            onItemClick(department)
        }
    }

    override fun getItemCount() = departmentList.size

    //Cap nhat danh sach hien thi va thong bao cho Adapter de cap nhat lai giao dien
    fun updateList(filteredList: List<Department>) {
        departmentList = filteredList
        notifyDataSetChanged()
    }
}
