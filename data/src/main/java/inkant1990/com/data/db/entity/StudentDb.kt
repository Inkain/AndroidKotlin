package inkant1990.com.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import inkant1990.com.data.entity.DataEntity

@Entity(tableName = "Students")
data class StudentDb(
    @PrimaryKey
    @ColumnInfo(name = "objectId")
    val id: String,
    val name: String,
    val age: Int, val image: String
) : DataEntity