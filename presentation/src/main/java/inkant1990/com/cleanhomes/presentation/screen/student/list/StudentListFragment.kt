package inkant1990.com.cleanhomes.presentation.screen.student.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.jakewharton.rxbinding2.widget.RxTextView
import inkant1990.com.cleanhomes.R
import inkant1990.com.cleanhomes.databinding.FragmentStudentListBinding
import inkant1990.com.cleanhomes.presentation.base.BaseMvvmFragment
import inkant1990.com.cleanhomes.presentation.screen.student.StudentRouter
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

class StudentListFragment : BaseMvvmFragment<StudentListViewModel, StudentRouter, FragmentStudentListBinding>() {

    companion object {

        fun getInstance() = StudentListFragment()
    }

    override fun prodiveViewModel(): StudentListViewModel {
        return ViewModelProviders.of(this).get(StudentListViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_student_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHome10StudentList.adapter = viewModel.adapter
        binding.rvHome10StudentList.layoutManager = LinearLayoutManager(context)
        binding.rvHome10StudentList.setHasFixedSize(true)
        RxTextView.textChanges(binding.etSearhHome10)
            .throttleFirst(500L, TimeUnit.MILLISECONDS)
            .subscribeBy {
                viewModel.search(it.toString())
            }
    }

}