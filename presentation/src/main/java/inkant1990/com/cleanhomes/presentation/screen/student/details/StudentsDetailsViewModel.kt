package inkant1990.com.cleanhomes.presentation.screen.student.details

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import inkant1990.com.cleanhomes.factory.UseCaseProvider
import inkant1990.com.cleanhomes.presentation.base.BaseViewModel
import inkant1990.com.cleanhomes.presentation.screen.student.StudentRouter
import inkant1990.com.domain.entity.Student
import io.reactivex.rxkotlin.subscribeBy

class StudentsDetailsViewModel : BaseViewModel<StudentRouter>() {
    val useCase = UseCaseProvider.provideStudentListUseCase()
    var name = ObservableField<String>("")
    var age = ObservableField<String>("")
    var image = ObservableField<String>("default")
    val visiblityMain = ObservableBoolean(true)
    val progress = ObservableBoolean(false)
    val btChange = ObservableBoolean(false)
    val visiblitySave = ObservableBoolean(true)
    var id: String? = null
    fun setStudentId(id: String?) {
        if (id != null) {
            visiblityMain.set(false)
            btChange.set(true)
            progress.set(true)
            visiblitySave.set(false)
            addToDisposable(
                useCase
                    .get(id)
                !!.subscribeBy(
                    onNext = {
                        this.id = it.id
                        this.image.set(it.image)
                        this.name.set(it.name)
                        this.age.set(it.age.toString())
                        visiblityMain.set(true)
                        progress.set(false)

                    },
                    onError = {
                        router?.showError(it)
                    })
            )
        }
    }

    fun delete(v: View) {
        addToDisposable(
            useCase.delete(id.toString()).subscribeBy(
                onError = {
                    router?.showError(it)
                })
        )
        router?.goToStudentList()
        if (!router!!.checkLand()) router?.removeFragment()
    }

    fun update(v: View) {
        addToDisposable(
            useCase.update(
                Student(
                    null,
                    this.name.get().toString(),
                    this.age.get()!!.toInt(),
                    this.image.get().toString()
                ), this.id.toString()
            ).subscribeBy(
                onError = {
                    router?.showError(it)
                })
        )
        router?.goToStudentList()
        if (!router!!.checkLand()) router?.removeFragment()
    }

    fun save(v: View) {
        useCase.put(Student(null, name.get().toString(), age.get()!!.toInt(), image.get().toString()))
            .subscribeBy(onError = {
                router?.showError(it)
            })
        router?.goToStudentList()
        if (!router!!.checkLand()) router?.removeFragment()
    }
}