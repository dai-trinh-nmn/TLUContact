package com.example.tlucontact

data class Department(
    val maDonVi: String = "",
    val tenDonVi: String = "",
    val diaChi: String = "",
    val logo: String = "",
    val dienThoai: String = "",
    val email: String = "",
    val fax: String = "",
    val donViCha: String = "",
    val donViCon: List<String> = emptyList()
)