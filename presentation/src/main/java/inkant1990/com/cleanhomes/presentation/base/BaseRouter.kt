package inkant1990.com.cleanhomes.presentation.base

import android.support.v4.app.FragmentManager
import android.util.Log
import android.widget.Toast

abstract class BaseRouter<A : BaseActivity>(val activity: A) {

    fun goBack() {


            activity.onBackPressed()

    }

    fun showError(e: Throwable) {
        Toast.makeText(activity.baseContext,e.message,Toast.LENGTH_LONG).show()
        Log.v("Error ", e.toString())

    }

    fun replaceFragment(
        fragmentManager: FragmentManager,
        fragment: BaseFragment,
        containerResId: Int, addToBackStack: Boolean = false
    ) {

        var fragmentTransition = fragmentManager.beginTransaction()

        fragmentTransition.replace(
            containerResId, fragment,
            fragment::class.java.canonicalName
        )

        if (addToBackStack) {
            fragmentTransition.addToBackStack(null)
        }

        fragmentTransition.commit()
    }

    fun removeFragment(fragmentManager: FragmentManager, containerResId: Int) {
        var fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.remove(fragmentManager?.findFragmentById(containerResId)!!).commit()

    }
}