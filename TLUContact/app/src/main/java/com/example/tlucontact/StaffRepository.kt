package com.example.tlucontact

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class StaffRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertStaff(staff: Staff) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NAME, staff.name)
            put(DatabaseHelper.COLUMN_POSITION, staff.position)
            put(DatabaseHelper.COLUMN_PHONE, staff.phone)
            put(DatabaseHelper.COLUMN_EMAIL, staff.email)
            put(DatabaseHelper.COLUMN_DEPARTMENT, staff.department)
            put(DatabaseHelper.COLUMN_DEPARTMENT_ID, staff.departmentId)
        }
        db.insert(DatabaseHelper.TABLE_STAFF, null, values)
        db.close()
    }

    fun getAllStaff(): List<Staff> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_STAFF,
            null, null, null, null, null, null
        )
        val staffList = mutableListOf<Staff>()
        with(cursor) {
            while (moveToNext()) {
                val staff = Staff(
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_POSITION)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DEPARTMENT)),
                    getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DEPARTMENT_ID))
                )
                staffList.add(staff)
            }
        }
        cursor.close()
        db.close()
        return staffList
    }

    fun searchStaff(query: String): List<Staff> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseHelper.TABLE_STAFF,
            null,
            "${DatabaseHelper.COLUMN_NAME} LIKE ?",
            arrayOf("%$query%"),
            null, null, null
        )
        val staffList = mutableListOf<Staff>()
        with(cursor) {
            while (moveToNext()) {
                val staff = Staff(
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_POSITION)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)),
                    getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DEPARTMENT)),
                    getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_DEPARTMENT_ID))
                )
                staffList.add(staff)
            }
        }
        cursor.close()
        db.close()
        return staffList
    }
}