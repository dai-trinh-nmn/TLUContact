package com.example.tlucontact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tlucontact.databinding.ActivityContactDetailBinding

//Activity hien thi chi tiet lien he cua mot phong ban
class ContactDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Thiet lap toolbar va nut back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Chi tiết liên hệ")
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        //Nhan du lieu tu Intent
        val name = intent.getStringExtra("EXTRA_NAME") ?: "Không có tên"
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: "Không có số điện thoại"
        val address = intent.getStringExtra("EXTRA_ADDRESS") ?: "Không có địa chỉ"
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: "Không có email"

        // Gan du lieu cho TextView
        binding.tvDetailName.text = "Tên đơn vị: $name"
        binding.tvDetailPhone.text = "Số điện thoại: $phone"
        binding.tvDetailAddress.text = "Địa chỉ: $address"
        binding.tvDetailEmail.text = "Email: $email"
    }
}
