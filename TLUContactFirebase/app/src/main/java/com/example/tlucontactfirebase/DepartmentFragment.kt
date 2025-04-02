package com.example.tlucontactfirebase

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tlucontact.Department
import com.example.tlucontact.DepartmentAdapter
import com.example.tlucontactfirebase.databinding.FragmentDepartmentBinding // Import binding
import com.google.firebase.firestore.FirebaseFirestore

class DepartmentFragment : Fragment() {
    private var _binding: FragmentDepartmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var departmentList: MutableList<Department>
    private lateinit var adapter: DepartmentAdapter
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepartmentBinding.inflate(inflater, container, false) // Sử dụng binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        departmentList = mutableListOf()
        adapter = DepartmentAdapter(departmentList) { department ->
            val intent = Intent(requireContext(), DepartmentDetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", department.tenDonVi)
                putExtra("EXTRA_PHONE", department.dienThoai)
                putExtra("EXTRA_ADDRESS", department.diaChi)
                putExtra("EXTRA_EMAIL", department.email)
                // Thêm các extra cho các trường mới
            }
            startActivity(intent)
        }

        binding.recyclerViewDepartments.layoutManager = LinearLayoutManager(requireContext()) // Sử dụng binding
        binding.recyclerViewDepartments.adapter = adapter // Sử dụng binding

        loadDepartmentsFromFirestore()
    }

    private fun loadDepartmentsFromFirestore() {
        db.collection("departments").get()
            .addOnSuccessListener { result ->
                departmentList.clear()
                for (document in result) {
                    val department = document.toObject(Department::class.java)
                    departmentList.add(department)
                }
                adapter.updateList(departmentList)
            }
            .addOnFailureListener { _ -> // Thay thế exception bằng _
                // Xử lý lỗi
            }
    }

    fun performSearch(query: String?) {
        val filteredList = departmentList.filter { it.tenDonVi.contains(query.orEmpty(), ignoreCase = true) }
        adapter.updateList(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}