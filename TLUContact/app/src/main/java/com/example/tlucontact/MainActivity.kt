package com.example.tlucontact

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.tlucontact.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var departmentRepository: DepartmentRepository
    private lateinit var staffRepository: StaffRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        departmentRepository = DepartmentRepository(this)
        staffRepository = StaffRepository(this)

        // Set up search icon color
        val searchView = findViewById<SearchView>(R.id.searchView)
        val searchIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_button)
        searchIcon.setColorFilter(Color.parseColor("#FFFFFF"))

        // Set up BottomNavigationView event
        binding.bottomNavigation.setOnNavigationItemSelectedListener(navListener)

        // Load default fragment (Department directory)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container, DepartmentFragment())
                .commit()
        }

        // Set up search event
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                performSearch(newText)
                return true
            }
        })
    }

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

    private fun performSearch(query: String?) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment is DepartmentFragment) {
            currentFragment.performSearch(query)
        } else if (currentFragment is StaffFragment) {
            currentFragment.performSearch(query)
        }
    }
}