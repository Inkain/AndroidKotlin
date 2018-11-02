package inkant1990.com.data.net

import inkant1990.com.data.entity.StudentResponse
import inkant1990.com.data.entity.Students
import io.reactivex.Observable
import retrofit2.http.*

interface RestApi {

    @GET("Students")
    fun getStudents(): Observable<List<StudentResponse>>

    @GET("Students/{id}")
    fun getStudentsById(@Path("id") id: String): Observable<StudentResponse>

    @Headers("Content-Type:application/json")
    @PUT("Students/{id}")
    fun updateStudent(
        @Path("id") id: String,
        @Body student: Students
    ): Observable<StudentResponse>

    @Headers("Content-Type:application/json")
    @PUT("Students")
    fun putStudent(
        @Body student: Students
    ): Observable<StudentResponse>

    @Headers("Content-Type:application/json")
    @DELETE("Students/{id}")
    fun deleteStudent(@Path("id") id: String): Observable<String>

    @GET("Students")
    fun searchStudent(@Query("where") name: String?): Observable<List<StudentResponse>>
}
