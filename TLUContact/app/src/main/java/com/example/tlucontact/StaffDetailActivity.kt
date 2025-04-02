package com.example.tlucontact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tlucontact.databinding.ActivityStaffDetailBinding

//Activity hien thi chi tiet lien he cua mot can bo
class StaffDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStaffDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaffDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Thiet lap toolbar va nut back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Chi tiết cán bộ")
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        //Nhan du lieu tu Intent
        val name = intent.getStringExtra("EXTRA_NAME") ?: "Không có tên"
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: "Không có số điện thoại"
        val position = intent.getStringExtra("EXTRA_POSITION") ?: "Không có chức vụ"
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: "Không có email"
        val department = intent.getStringExtra("EXTRA_DEPARTMENT") ?: "Không có đơn vị"

        // Gan du lieu cho TextView
        binding.tvDetailName.text = "Tên cán bộ: $name"
        binding.tvDetailPosition.text = "Chức vụ: $position"
        binding.tvDetailPhone.text = "Số điện thoại: $phone"
        binding.tvDetailEmail.text = "Email: $email"
        binding.tvDetailDepartment.text = "Đơn vị: $department"
    }
}