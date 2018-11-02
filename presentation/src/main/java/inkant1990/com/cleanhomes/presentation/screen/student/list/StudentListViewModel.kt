package inkant1990.com.cleanhomes.presentation.screen.student.list

import android.databinding.ObservableField
import android.view.View
import inkant1990.com.cleanhomes.factory.UseCaseProvider
import inkant1990.com.cleanhomes.presentation.base.BaseViewModel
import inkant1990.com.cleanhomes.presentation.screen.student.StudentRouter
import inkant1990.com.cleanhomes.presentation.screen.student.adapters.StudentListAdapter
import inkant1990.com.domain.entity.Search
import inkant1990.com.domain.interactor.GetStudentsUseCase
import io.reactivex.rxkotlin.subscribeBy

class StudentListViewModel : BaseViewModel<StudentRouter>() {
    val studentsUseCase: GetStudentsUseCase = UseCaseProvider.provideStudentListUseCase()
    var adapter: StudentListAdapter = StudentListAdapter()
    var searchData = ObservableField<String>("")

    init {
        adapter.setOnItemClickListener(object : StudentListAdapter.OnItemClickListener {
            override fun onClick(id: String) {
                router!!.goToStudentDetails(id)
            }
        })

        val disposable = studentsUseCase.get().subscribeBy(

            onNext = {
                adapter.setStudents(it)
                adapter.notifyDataSetChanged()
            },
            onError = {
                router?.showError(it)
            })
        addToDisposable(disposable)
    }

    fun addStudent(v: View) {
        router?.goToStudentDetails(null)
    }

    fun search(name: String?) {
        val studentSearch = Search(name)
        val disposable = studentsUseCase.search(studentSearch)
            .subscribeBy(
                onNext = {
                    adapter.setStudents(it)
                    adapter.notifyDataSetChanged()
                },
                onError = {
                    router?.showError(it)
                }
            )
        addToDisposable(disposable)
    }
}


