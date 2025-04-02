package com.example.tlucontact

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tlucontact.databinding.FragmentStaffBinding

class StaffFragment : Fragment() {
    private var _binding: FragmentStaffBinding? = null
    private val binding get() = _binding!!
    private lateinit var staffRepository: StaffRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStaffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        staffRepository = StaffRepository(requireContext())

        // Lấy danh sách nhân viên từ SQLite
        val staffList = staffRepository.getAllStaff()

        // Tạo adapter cho RecyclerView và thiết lập sự kiện click cho từng item
        val adapter = StaffAdapter(staffList) { staff ->
            val intent = Intent(requireContext(), StaffDetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", staff.name)
                putExtra("EXTRA_POSITION", staff.position)
                putExtra("EXTRA_DEPARTMENT_ID", staff.departmentId)
                putExtra("EXTRA_DEPARTMENT", staff.department)
                putExtra("EXTRA_PHONE", staff.phone)
                putExtra("EXTRA_EMAIL", staff.email)
            }
            startActivity(intent)
        }

        // Thiết lập layout manager và adapter cho RecyclerView
        binding.recyclerViewStaff.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewStaff.adapter = adapter
    }

    // Hàm thực hiện tìm kiếm và cập nhật danh sách hiển thị
    fun performSearch(query: String?) {
        val filteredList = staffRepository.searchStaff(query.orEmpty())
        (binding.recyclerViewStaff.adapter as StaffAdapter).updateList(filteredList)
    }

    // Giải phóng tài nguyên khi view bị hủy
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}