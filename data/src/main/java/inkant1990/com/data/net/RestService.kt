package inkant1990.com.data.net

import com.google.gson.Gson
import inkant1990.com.data.entity.StudentResponse
import inkant1990.com.data.entity.Students
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RestService constructor(private val apiUrl: String) {

    private val restApi: RestApi

    init {
        val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        okHttpBuilder.addInterceptor(logging)
        val gson = Gson()
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpBuilder.build())
            .build()
        restApi = retrofit.create(RestApi::class.java)
    }

    fun getStudents(): Observable<List<StudentResponse>> {
        return restApi.getStudents()
    }

    fun putStudent(student: Students): Observable<StudentResponse> {
        return restApi.putStudent(student)
    }

    fun getStudentById(id: String): Observable<StudentResponse> {
        return restApi.getStudentsById(id)
    }

    fun updateStudent(student: Students, id: String): Observable<StudentResponse> {
        return restApi.updateStudent(id, student)
    }

    fun deleteStudent(id: String): Observable<String> {
        return restApi.deleteStudent(id)
    }

    fun searchStudent(name: String?): Observable<List<StudentResponse>> {
        return restApi.searchStudent(name)
    }
}