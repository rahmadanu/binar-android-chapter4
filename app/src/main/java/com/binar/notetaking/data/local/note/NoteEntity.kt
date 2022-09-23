package com.binar.notetaking.data.local.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_information")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null
) {
}