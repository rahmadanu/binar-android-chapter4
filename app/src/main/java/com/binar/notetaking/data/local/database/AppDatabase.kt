package com.binar.notetaking.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.binar.notetaking.data.local.database.note.NoteDao
import com.binar.notetaking.data.local.database.note.NoteEntity
import com.binar.notetaking.data.local.database.user.UserDao
import com.binar.notetaking.data.local.database.user.UserEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [UserEntity::class, NoteEntity::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun noteDao(): NoteDao

    companion object {
        private const val DB_NAME = "NoteTaking.db"

        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    val passphrase: ByteArray =
                        SQLiteDatabase.getBytes("NoteTaking-hashed".toCharArray())
                    val factory = SupportFactory(passphrase)

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .openHelperFactory(factory)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}