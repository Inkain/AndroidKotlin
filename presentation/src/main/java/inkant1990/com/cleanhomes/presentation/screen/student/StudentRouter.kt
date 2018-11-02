package inkant1990.com.cleanhomes.presentation.screen.student

import android.view.View
import inkant1990.com.cleanhomes.R
import inkant1990.com.cleanhomes.presentation.base.BaseRouter
import inkant1990.com.cleanhomes.presentation.screen.student.details.StudentDetailsFragment
import inkant1990.com.cleanhomes.presentation.screen.student.list.StudentListFragment

class StudentRouter(activity: StudentActivity) : BaseRouter<StudentActivity>(activity) {

    fun goToStudentList() {
        replaceFragment(
            activity.supportFragmentManager,
            StudentListFragment.getInstance(),
            R.id.container, false
        )
    }

    fun checkLand(): Boolean {
        val view = activity.findViewById<View>(R.id.containerDetails)
        return view == null
    }

    fun goToStudentDetails(id: String?) {
        val view = activity.findViewById<View>(R.id.containerDetails)
        val stack: Boolean = activity.supportFragmentManager.backStackEntryCount == 0
        if (view == null) {
            replaceFragment(
                activity.supportFragmentManager,
                StudentDetailsFragment.getInstance(id),
                R.id.container, stack
            )

        } else {
            replaceFragment(
                activity.supportFragmentManager,
                StudentDetailsFragment.getInstance(id),
                R.id.containerDetails, stack
            )
        }
    }

    fun removeFragment() {
        removeFragment(activity.supportFragmentManager, R.id.containerDetails)
    }

}
