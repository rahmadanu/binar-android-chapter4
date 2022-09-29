package com.binar.notetaking.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.binar.notetaking.data.local.database.note.NoteDao
import com.binar.notetaking.data.local.database.note.NoteEntity
import com.binar.notetaking.data.local.database.user.UserDao
import com.binar.notetaking.data.local.database.user.UserEntity

@Database(entities = [UserEntity::class, NoteEntity::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao

    companion object {

        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}