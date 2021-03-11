package cvdevelopers.takehome.utils.ui

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import cvdevelopers.takehome.dagger.app.ApplicationComponent
import cvdevelopers.takehome.dagger.DaggerApplication

abstract class BaseFragment(@LayoutRes resId: Int) : Fragment(resId) {

    abstract fun injectDependencies()

    val appComponent: ApplicationComponent by lazy {
        (activity?.applicationContext as DaggerApplication).getApplicationComponent()
    }

    override fun onAttach(context: Context) {
        injectDependencies()
        super.onAttach(context)
    }
}