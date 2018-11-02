package inkant1990.com.cleanhomes.presentation.screen.student.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import inkant1990.com.cleanhomes.R
import inkant1990.com.cleanhomes.databinding.FragmentStudentsDetailsBinding
import inkant1990.com.cleanhomes.presentation.base.BaseMvvmFragment
import inkant1990.com.cleanhomes.presentation.screen.student.StudentRouter

class StudentDetailsFragment :
    BaseMvvmFragment<StudentsDetailsViewModel, StudentRouter, FragmentStudentsDetailsBinding>() {
    companion object {
        private const val ID_EXTRA = "ID_EXTRA"
        fun getInstance(id: String?): StudentDetailsFragment {
            val fragment = StudentDetailsFragment()
            if (id != null) {
                val bundle = Bundle()
                bundle.putString("ID_EXTRA", id)
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    override fun prodiveViewModel(): StudentsDetailsViewModel {

        return ViewModelProviders.of(this).get(StudentsDetailsViewModel::class.java)

    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_students_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val id = arguments?.get(ID_EXTRA) as String
            viewModel.setStudentId(id)
        } else {
            router?.goBack()
        }
    }
}