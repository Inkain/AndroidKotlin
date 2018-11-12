package inkant1990.com.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import inkant1990.com.data.Utils
import inkant1990.com.data.db.dao.StudentDao
import inkant1990.com.data.entity.transformToDb
import inkant1990.com.data.entity.transformToDomain
import inkant1990.com.data.entity.transformToRequest
import inkant1990.com.data.net.RestService
import inkant1990.com.domain.entity.Search
import inkant1990.com.domain.entity.Student
import inkant1990.com.domain.repository.StudentRepository
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableJust

class StudentRepositoryImpl constructor(val apiService: RestService, val studentDao: StudentDao, val context: Context) :
    StudentRepository {
    companion object {
        const val TIME_BUFER = 1000

    }

    private var lastTimeUpdate = 0L
    private fun checkConnect(): Boolean {
        val connect = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (connect.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || connect.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
    }

    override fun get(id: String): Observable<Student> {
        return studentDao.getByID(id).toObservable().take(1)
            .flatMap {
                if ((System.currentTimeMillis() - lastTimeUpdate > TIME_BUFER) && checkConnect()) {
                    apiService.getStudentById(id)
                        .map { studentResponse -> studentResponse.transformToDomain() }
                } else {
                    ObservableJust(it.transformToDomain())
                }
            }
    }


    override fun get(): Observable<List<Student>> {
        return studentDao.getAll().toObservable().take(1)
            .flatMap { studentDbList ->
                if ((studentDbList.isEmpty() || System.currentTimeMillis() - lastTimeUpdate > TIME_BUFER) && checkConnect()) {
                    apiService.getStudents()
                        .doOnNext {
                            lastTimeUpdate = System.currentTimeMillis()
                            val list = it.map { it.transformToDb() }
                            studentDao.deleteAll()
                            studentDao.insert(list)
                        }
                        .map { list ->
                            list.map { student -> student.transformToDomain() }
                        }
                        .onErrorReturn {
                            if (studentDbList.isEmpty()) {
                                throw it
                            } else {
                                studentDbList
                                    .map { it ->
                                        it.transformToDomain()
                                    }
                            }
                        }
                } else {
                    Observable.just(studentDbList).doOnNext {
                    }.map { list ->
                        list.map { student -> student.transformToDomain() }
                    }
                }
            }
    }


    override fun search(search: Search): Observable<List<Student>> {
        return studentDao.search(search.name).toObservable().take(1)
            .flatMap {
                if ((it.isEmpty() || System.currentTimeMillis() - lastTimeUpdate > TIME_BUFER) && checkConnect()) {
                    apiService.searchStudent(Utils.transformToRequest(search.name))
                        .map { list ->
                            list.map { studentResponse -> studentResponse.transformToDomain() }
                        }
                } else {
                    ObservableJust(it).map { list ->
                        list.map { studentDb -> studentDb.transformToDomain() }

                    }
                }
            }

    }

    override fun update(student: Student, studentId: String): Observable<Student> {

        return apiService.updateStudent(student.transformToRequest(), studentId).doOnNext {
            studentDao.update(it.transformToDb())

        }.map { it.transformToDomain() }
    }

    override fun delete(studentId: String): Observable<String> {
        return apiService.deleteStudent(studentId).doOnComplete { studentDao.deleteById(studentId) }
    }


    override fun put(student: Student): Observable<Student> {
        return apiService.putStudent(student.transformToRequest()).doOnNext {
            studentDao.insert(listOf(it.transformToDb()))

        }.map { it.transformToDomain() }

    }
}