package inkant1990.com.data.repository

import inkant1990.com.data.Utils
import inkant1990.com.data.entity.transformToDomain
import inkant1990.com.data.net.RestService
import inkant1990.com.domain.entity.Search
import inkant1990.com.domain.entity.Student
import inkant1990.com.domain.repository.StudentRepository
import io.reactivex.Observable

class StudentRepositoryImpl constructor(val apiService: RestService) : StudentRepository {
    override fun get(id: String): Observable<Student> {
        return apiService.getStudentById(id).map { it.transformToDomain() }
    }


    override fun get(): Observable<List<Student>> {
        return apiService.getStudents()
            .map { list ->
                list.map { student -> student.transformToDomain() }
            }
    }

    override fun search(search: Search): Observable<List<Student>> {

        return apiService.searchStudent(Utils.transformToRequest(search.name)).map { it.map { studentResponse -> studentResponse.transformToDomain() } }
    }

    override fun update(student: Student, studentId: String): Observable<Student> {
        return apiService.updateStudent(student.transformToDomain(), studentId).map { it.transformToDomain() }
    }

    override fun delete(studentId: String): Observable<String> {
        return apiService.deleteStudent(studentId)
    }

    override fun put(student: Student): Observable<Student> {
        return apiService.putStudent(student.transformToDomain()).map { it.transformToDomain() }
    }
}