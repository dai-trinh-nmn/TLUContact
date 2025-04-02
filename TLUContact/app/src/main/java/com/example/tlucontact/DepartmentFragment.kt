package com.example.tlucontact

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tlucontact.databinding.FragmentDepartmentBinding


class DepartmentFragment : Fragment() {
    private var _binding: FragmentDepartmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var departmentList: List<Department>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepartmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Tao danh sach cac phong ban
        departmentList = listOf(
            Department("Phòng Đào tạo", "0123456789", "175 Tây Sơn, Đống Đa, Hà Nội", "example@wru.edu.vn"),
            Department("Phòng Công tác Sinh viên", "0987654321", "175 Tây Sơn, Đống Đa, Hà Nội", "example@wru.edu.vn"),
            Department("Khoa Công nghệ thông tin", "0241234567", "175 Tây Sơn, Đống Đa, Hà Nội", "it@wru.edu.vn"),
            Department("Khoa Kinh tế", "0249876543", "175 Tây Sơn, Đống Đa, Hà Nội", "economic@wru.edu.vn"),
            Department("Khoa Cơ khí", "0245678901", "175 Tây Sơn, Đống Đa, Hà Nội", "mechanical@wru.edu.vn"),
            Department("Khoa Điện - Điện tử", "0243456789", "175 Tây Sơn, Đống Đa, Hà Nội", "electrical@wru.edu.vn"),
            Department("Khoa Xây dựng", "0247890123", "175 Tây Sơn, Đống Đa, Hà Nội", "construction@wru.edu.vn"),
            Department("Khoa Ngoại ngữ", "0246543210", "175 Tây Sơn, Đống Đa, Hà Nội", "foreign@wru.edu.vn"),
            Department("Trung tâm Thư viện", "0242345678", "175 Tây Sơn, Đống Đa, Hà Nội", "library@wru.edu.vn"),
            Department("Trung tâm Giáo dục thể chất", "0248901234", "175 Tây Sơn, Đống Đa, Hà Nội", "sport@wru.edu.vn"),
            Department("Phòng Hành chính - Tổng hợp", "0244567890", "175 Tây Sơn, Đống Đa, Hà Nội", "admin@wru.edu.vn"),
            Department("Phòng Tài chính - Kế toán", "0241098765", "175 Tây Sơn, Đống Đa, Hà Nội", "finance@wru.edu.vn")
        )

        //Tao adapter cho RecyclerView va thiet lap su kien click cho tung item
        val adapter = DepartmentAdapter(departmentList) { department ->
            val intent = Intent(requireContext(), ContactDetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", department.name)
                putExtra("EXTRA_PHONE", department.phone)
                putExtra("EXTRA_ADDRESS", department.address)
                putExtra("EXTRA_EMAIL", department.email)
            }
            startActivity(intent)
        }

        //Thiet lap layout manager va adapter cho RecyclerView
        binding.recyclerViewDepartments.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDepartments.adapter = adapter
    }

    //Ham thuc hien tim kiem va cap nhat danh sach hien thi
    fun performSearch(query: String?) {
        val filteredList = departmentList.filter { it.name.contains(query.orEmpty(), ignoreCase = true) }
        (binding.recyclerViewDepartments.adapter as DepartmentAdapter).updateList(filteredList)
    }

    //Giai phong tai nguyen khi view bi huy
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
