package inkant1990.com.data.db.dao

import android.arch.persistence.room.*
import inkant1990.com.data.db.entity.StudentDb
import io.reactivex.Flowable

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(studentDb: List<StudentDb>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(studentDb: StudentDb)

    @Query("SELECT * FROM Students WHERE name LIKE  :search || '%'")
    fun search(search: String?): Flowable<List<StudentDb>>

    @Query("SELECT * FROM Students")
    fun getAll(): Flowable<List<StudentDb>>

    @Query("DELETE FROM Students")
    fun deleteAll()

    @Query("SELECT * FROM Students WHERE objectId= :id LIMIT 1")
    fun getByID(id: String): Flowable<StudentDb>


    @Query("DELETE FROM Students WHERE objectId = :id")
    fun deleteById(id: String)

}