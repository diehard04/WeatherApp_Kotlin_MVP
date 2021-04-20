package com.example.weatherapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.weatherapp.data.WeatherInfoModel

/**
 * Created by DieHard_04 on 20-04-2021.
 */

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "Weather"
        private val TABLE_CONTACTS = "WeatherTable"
        private val KEY_ID = "id"
        private val KEY_CITY_NAME = "cityName"
        private val KEY_CITY_TEMP = "cityTemp"
        private val KEY_MIN_TEMP = "minTemp"
        private val KEY_MAX_TEMP = "maxTemp"
        private val KEY_WIND_SPEED = "windSpeed"
        private val KEY_CLOUD = "cloud"
        private val KEY_CURRENT_TIME = "currentTime"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_CITY_NAME + " TEXT,"
                + KEY_CITY_TEMP + " TEXT,"+ KEY_MIN_TEMP + " TEXT,"
                + KEY_MAX_TEMP + " TEXT,"+ KEY_WIND_SPEED + " TEXT,"
                + KEY_CLOUD + " TEXT," + KEY_CURRENT_TIME + " TEXT" + ")")

        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addWeatherReport(model: WeatherInfoModel, currentTime: String):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_CITY_NAME, model.getCityName())
        contentValues.put(KEY_CITY_TEMP, model.getTemp())
        contentValues.put(KEY_MIN_TEMP, model.getMinTemp())
        contentValues.put(KEY_MAX_TEMP, model.getMaxTemp())
        contentValues.put(KEY_WIND_SPEED, model.getWindSpeed())
        contentValues.put(KEY_CLOUD, model.getCloudDesc())
        contentValues.put(KEY_CURRENT_TIME, currentTime)

        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to read data
    fun getSavedWeatherReport():WeatherInfoModel{
        val model= WeatherInfoModel()
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.query(TABLE_CONTACTS, null, null, null, null, null, null, null);
        }catch (e: SQLiteException) {
            print(e.message)
        }

        if (cursor!!.moveToFirst()) {
            do {
                model.setCityName(cursor.getString(cursor.getColumnIndex(KEY_CITY_NAME)))
                model.setTemp(cursor.getString(cursor.getColumnIndex(KEY_CITY_TEMP)))
                model.setMinTemp(cursor.getString(cursor.getColumnIndex(KEY_MIN_TEMP)))
                model.setMaxTemp(cursor.getString(cursor.getColumnIndex(KEY_MAX_TEMP)))
                model.setWindSpeed(cursor.getString(cursor.getColumnIndex(KEY_WIND_SPEED)))
                model.setCloudDesc(cursor.getString(cursor.getColumnIndex(KEY_CLOUD)))
            } while (cursor.moveToNext())
        }
        return model
    }

    fun getCurrentSavedTime():String? {
        val projection = arrayOf(KEY_CURRENT_TIME)
        //val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        var savedTime:String ?= null
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.query(TABLE_CONTACTS, projection, null, null, null, null, null, null);
        }catch (e: SQLiteException) {
            print(e.message)
        }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    savedTime = cursor.getString(cursor.getColumnIndex(KEY_CURRENT_TIME))
                } while (cursor.moveToNext())
            }
        }
        return savedTime
    }

//    //method to update data
//    fun updateEmployee(emp: EmpModelClass):Int{
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(KEY_ID, emp.userId)
//        contentValues.put(KEY_NAME, emp.userName) // EmpModelClass Name
//        contentValues.put(KEY_EMAIL,emp.userEmail ) // EmpModelClass Email
//
//        // Updating Row
//        val success = db.update(TABLE_CONTACTS, contentValues,"id="+emp.userId,null)
//        //2nd argument is String containing nullColumnHack
//        db.close() // Closing database connection
//        return success
//    }
}