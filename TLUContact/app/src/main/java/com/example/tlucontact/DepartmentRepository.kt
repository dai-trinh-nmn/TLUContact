package com.example.tlucontact

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class DepartmentRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertDepartment(department: Department) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME, department.name)
            put(DatabaseHelper.COLUMN_PHONE, department.phone)
            put(DatabaseHelper.COLUMN_ADDRESS, department.address)
            put(DatabaseHelper.COLUMN_EMAIL, department.email)
        }
        db.insert(DatabaseHelper.TABLE_DEPARTMENT, null, values)
        db.close()
    }

    fun getAllDepartments(): List<Department> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_DEPARTMENT,
            null, null, null, null, null, null
        )
        val departments = mutableListOf<Department>()
        with(cursor) {
            while (moveToNext()) {
                val department = Department(
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL))
                )
                departments.add(department)
            }
        }
        cursor.close()
        db.close()
        return departments
    }

    fun searchDepartments(query: String): List<Department> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_DEPARTMENT,
            null,
            "${DatabaseHelper.COLUMN_NAME} LIKE ?",
            arrayOf("%$query%"),
            null, null, null
        )
        val departments = mutableListOf<Department>()
        with(cursor) {
            while (moveToNext()) {
                val department = Department(
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL))
                )
                departments.add(department)
            }
        }
        cursor.close()
        db.close()
        return departments
    }
}