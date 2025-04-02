package com.example.tlucontact

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.tlucontact.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

//Activity chinh cua ung dung
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Thiet lap mau sac cho icon tim kiem
        val searchView = findViewById<SearchView>(R.id.searchView)
        val searchIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_button)
        searchIcon.setColorFilter(Color.parseColor("#FFFFFF"))

        //Thiet lap su kien cho BottomNavigationView
        binding.bottomNavigation.setOnNavigationItemSelectedListener(navListener)

        //Load fragment mac dinh (Danh bạ đơn vị)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, DepartmentFragment())
                .commit()
        }

        //Thiet lap su kien tim kiem
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                performSearch(query)
                return true // Trả về true để cho biết đã xử lý sự kiện
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                performSearch(newText)
                return true // Trả về true để cho biết đã xử lý sự kiện
            }
        })
    }

    //Listener cho BottomNavigationView
    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val selectedFragment: Fragment = when (item.itemId) {
            R.id.nav_department -> DepartmentFragment()
            R.id.nav_staff -> StaffFragment()
            else -> DepartmentFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, selectedFragment)
            .commit()
        true
    }

    //Ham thuc hien tim kiem va cap nhat danh sach hien thi
    private fun performSearch(query: String?) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment is DepartmentFragment) {
            currentFragment.performSearch(query)
        } else if (currentFragment is StaffFragment) {
            currentFragment.performSearch(query)
        }
    }
}