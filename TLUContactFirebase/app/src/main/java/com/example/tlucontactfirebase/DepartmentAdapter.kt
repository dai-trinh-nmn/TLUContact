package com.example.tlucontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tlucontactfirebase.R

class DepartmentAdapter(
    private var departmentList: List<Department>,
    private val onItemClick: (Department) -> Unit
) : RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder>() {

    class DepartmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tenDonVi: TextView = itemView.findViewById(R.id.tvDepartmentName)
        val dienThoai: TextView = itemView.findViewById(R.id.tvDepartmentPhone)
        val diaChi: TextView = itemView.findViewById(R.id.tvDepartmentAddress)
        val dienthoai: TextView = itemView.findViewById(R.id.tvDepartmentPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_department, parent, false)
        return DepartmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        val department = departmentList[position]
        holder.tenDonVi.text = department.tenDonVi
        holder.dienThoai.text = department.dienThoai
        holder.diaChi.text = department.diaChi

        holder.itemView.findViewById<CardView>(R.id.cardViewDepartment).setOnClickListener {
            onItemClick(department)
        }
    }

    override fun getItemCount() = departmentList.size

    fun updateList(filteredList: List<Department>) {
        departmentList = filteredList
        notifyDataSetChanged()
    }
}