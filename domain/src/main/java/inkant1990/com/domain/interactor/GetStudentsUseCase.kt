package inkant1990.com.domain.interactor

import inkant1990.com.domain.entity.Search
import inkant1990.com.domain.entity.Student
import inkant1990.com.domain.executor.PostExecutorThread
import inkant1990.com.domain.repository.StudentRepository
import io.reactivex.Observable

class GetStudentsUseCase constructor(
    private val studentRepository: StudentRepository, postExecutorThread: PostExecutorThread
) : BaseUseCase(postExecutorThread) {

    fun get(): Observable<List<Student>> {
        return studentRepository.get()
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun update(student: Student, id: String): Observable<Student> {

        return studentRepository.update(student, id).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }

    fun delete(id: String): Observable<String> {
        return studentRepository.delete(id).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }

    fun get(id: String): Observable<Student>? {
        return studentRepository.get(id)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }

    fun put(student: Student): Observable<Student> {
        return studentRepository.put(student).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }

    fun search(searchStudent: Search): Observable<List<Student>> {
        return studentRepository.search(searchStudent)
            .observeOn(postExecutorThread)
            .subscribeOn(workExecutorThread)
    }
}
