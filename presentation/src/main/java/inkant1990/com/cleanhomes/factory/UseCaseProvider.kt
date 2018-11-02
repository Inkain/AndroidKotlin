package inkant1990.com.cleanhomes.factory

import inkant1990.com.cleanhomes.executor.UIThread
import inkant1990.com.data.net.RestService
import inkant1990.com.data.repository.StudentRepositoryImpl
import inkant1990.com.domain.interactor.GetStudentsUseCase

object UseCaseProvider {

    val uiThread = UIThread()
    val restService =
        RestService("https://api.backendless.com/EFFBF319-D070-6778-FFAD-8A7DAF9F2300/39EEAF2B-7FB6-A6E3-FF92-5FC5EFB81800/data/")

    fun provideStudentListUseCase(): GetStudentsUseCase {
        val repository = StudentRepositoryImpl(restService)
        val useCase = GetStudentsUseCase(repository, uiThread)
        return useCase
    }
}