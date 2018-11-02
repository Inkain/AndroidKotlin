package inkant1990.com.cleanhomes.presentation.screen.student

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import inkant1990.com.cleanhomes.R
import inkant1990.com.cleanhomes.databinding.ActivityHome10StudentBinding
import inkant1990.com.cleanhomes.presentation.base.BaseMvvmActivity


class StudentActivity : BaseMvvmActivity<StudentViewModel, StudentRouter, ActivityHome10StudentBinding>() {
    override fun prodiveViewModel() = ViewModelProviders.of(this).get(StudentViewModel::class.java)


    override fun provideRouter(): StudentRouter {
        return StudentRouter(this)
    }

    override fun provideLayoutId() = R.layout.activity_home10_student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router.goToStudentList()
    }
}