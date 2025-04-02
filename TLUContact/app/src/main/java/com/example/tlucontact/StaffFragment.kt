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
    private lateinit var staffList: List<Staff>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStaffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tao danh sach cac Staff
        staffList = listOf(
            Staff("Nguyễn Văn A", "Giảng viên", "0123456789", "a@tlu.edu.vn", "Khoa CNTT"),
            Staff("Trần Thị B", "Trưởng phòng", "0987654321", "b@tlu.edu.vn", "Phòng Hành chính"),
            Staff("Lê Hoàng C", "Phó trưởng khoa", "0241122334", "c@tlu.edu.vn", "Khoa Kinh tế"),
            Staff("Phạm Thu D", "Giảng viên", "0245566778", "d@tlu.edu.vn", "Khoa Cơ khí"),
            Staff("Hoàng Minh E", "Trưởng bộ môn", "0249900112", "e@tlu.edu.vn", "Khoa Điện - Điện tử"),
            Staff("Đỗ Thị F", "Giảng viên", "0243344556", "f@tlu.edu.vn", "Khoa Xây dựng"),
            Staff("Vũ Đức G", "Nhân viên", "0247788990", "g@tlu.edu.vn", "Phòng Đào tạo"),
            Staff("Cao Thanh H", "Giảng viên", "0246677889", "h@tlu.edu.vn", "Khoa Ngoại ngữ"),
            Staff("Bùi Phương I", "Thủ thư", "0242233445", "i@tlu.edu.vn", "Trung tâm Thư viện"),
            Staff("Đinh Xuân K", "Huấn luyện viên", "0248899001", "k@tlu.edu.vn", "Trung tâm Giáo dục thể chất"),
            Staff("Hà Thị L", "Kế toán", "0241011223", "l@tlu.edu.vn", "Phòng Tài chính - Kế toán"),
            Staff("Trương Công M", "Giảng viên", "0123456790", "m@tlu.edu.vn", "Khoa CNTT"),
            Staff("Phan Thị N", "Trưởng phòng", "0987654322", "n@tlu.edu.vn", "Phòng Công tác Sinh viên"),
            Staff("Lưu Quang O", "Phó trưởng khoa", "0241122335", "o@tlu.edu.vn", "Khoa Kinh tế"),
            Staff("Quách Diệu P", "Giảng viên", "0245566779", "p@tlu.edu.vn", "Khoa Cơ khí"),
            Staff("Chu Tuấn Q", "Trưởng bộ môn", "0249900113", "q@tlu.edu.vn", "Khoa Điện - Điện tử"),
            Staff("Tạ Mỹ R", "Giảng viên", "0243344557", "r@tlu.edu.vn", "Khoa Xây dựng"),
            Staff("Mạc Văn S", "Nhân viên", "0247788991", "s@tlu.edu.vn", "Phòng Đào tạo"),
            Staff("Uông Thị T", "Giảng viên", "0246677890", "t@tlu.edu.vn", "Khoa Ngoại ngữ"),
            Staff("Yên Thị U", "Thủ thư", "0242233446", "u@tlu.edu.vn", "Trung tâm Thư viện"),
            Staff("Vương Đình V", "Huấn luyện viên", "0248899002", "v@tlu.edu.vn", "Trung tâm Giáo dục thể chất"),
            Staff("Triệu Thị X", "Kế toán", "0241011224", "x@tlu.edu.vn", "Phòng Tài chính - Kế toán"),
            Staff("Đặng Bá Y", "Giảng viên", "0123456791", "y@tlu.edu.vn", "Khoa CNTT"),
            Staff("Giang Thị Z", "Trưởng phòng", "0987654323", "z@tlu.edu.vn", "Phòng Hành chính"),
            Staff("Ngô Anh AA", "Phó trưởng khoa", "0241122336", "aa@tlu.edu.vn", "Khoa Kinh tế"),
            Staff("Diệp Thanh BB", "Giảng viên", "0245566780", "bb@tlu.edu.vn", "Khoa Cơ khí"),
            Staff("Kiều Xuân CC", "Trưởng bộ môn", "0249900114", "cc@tlu.edu.vn", "Khoa Điện - Điện tử"),
            Staff("Lâm Thu DD", "Giảng viên", "0243344558", "dd@tlu.edu.vn", "Khoa Xây dựng"),
            Staff("Ninh Văn EE", "Nhân viên", "0247788992", "ee@tlu.edu.vn", "Phòng Đào tạo"),
            Staff("Ôn Thị FF", "Giảng viên", "0246677891", "ff@tlu.edu.vn", "Khoa Ngoại ngữ")
        )

        //Tao adapter cho RecyclerView va thiet lap su kien click cho tung item
        val adapter = StaffAdapter(staffList) { staff ->
            val intent = Intent(requireContext(), StaffDetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", staff.name)
                putExtra("EXTRA_POSITION", staff.position)
                putExtra("EXTRA_PHONE", staff.phone)
                putExtra("EXTRA_EMAIL", staff.email)
                putExtra("EXTRA_DEPARTMENT", staff.department)
            }
            startActivity(intent)
        }

        //Thiet lap layout manager va adapter cho RecyclerView
        binding.recyclerViewStaff.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewStaff.adapter = adapter
    }

    //Ham thuc hien tim kiem
    fun performSearch(query: String?) {
        val filteredList = staffList.filter { it.name.contains(query.orEmpty(), ignoreCase = true) }
        (binding.recyclerViewStaff.adapter as StaffAdapter).updateList(filteredList)
    }

    //Giai phong tai nguyen khi view bi huy
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}