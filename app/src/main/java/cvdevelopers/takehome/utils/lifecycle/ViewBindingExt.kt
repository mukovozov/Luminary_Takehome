package cvdevelopers.takehome.utils.lifecycle

import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

@MainThread
inline fun <reified VB : ViewBinding> Fragment.viewBinding(
    crossinline viewBindingProduces: (View) -> VB
): Lazy<VB> {
    return lazy(LazyThreadSafetyMode.NONE) {
        viewBindingProduces.invoke(this.requireView())
    }
}