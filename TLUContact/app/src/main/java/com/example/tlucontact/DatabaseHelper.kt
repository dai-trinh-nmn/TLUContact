package com.example.tlucontact

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createDepartmentTable = """
            CREATE TABLE $TABLE_DEPARTMENT (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_PHONE TEXT,
                $COLUMN_ADDRESS TEXT,
                $COLUMN_EMAIL TEXT
            )
        """.trimIndent()
        db.execSQL(createDepartmentTable)

        val createStaffTable = """
            CREATE TABLE $TABLE_STAFF (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_POSITION TEXT,
                $COLUMN_PHONE TEXT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_DEPARTMENT TEXT,
                $COLUMN_DEPARTMENT_ID INTEGER,
                FOREIGN KEY($COLUMN_DEPARTMENT_ID) REFERENCES $TABLE_DEPARTMENT($COLUMN_ID)
            )
        """.trimIndent()
        db.execSQL(createStaffTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_DEPARTMENT")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STAFF")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "tlucontact.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_DEPARTMENT = "department"
        const val TABLE_STAFF = "staff"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_POSITION = "position"
        const val COLUMN_DEPARTMENT = "department"
        const val COLUMN_DEPARTMENT_ID = "department_id"
    }
}