package inkant1990.com.domain.repository

import inkant1990.com.domain.entity.Search
import inkant1990.com.domain.entity.Student
import io.reactivex.Observable

interface StudentRepository : BaseRepository {

    fun get(): Observable<List<Student>>
    fun get(id: String): Observable<Student>
    fun search(search: Search): Observable<List<Student>>
    fun update(student: Student, studentId: String): Observable<Student>
    fun delete(studentId: String): Observable<String>
    fun put(student: Student): Observable<Student>
}