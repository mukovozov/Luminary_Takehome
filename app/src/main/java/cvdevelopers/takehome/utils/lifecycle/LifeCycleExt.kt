package cvdevelopers.takehome.utils.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

inline fun <reified T : Any> MutableLiveData<T>.update(action: T.() -> T) {
    value = action.invoke(requireValue())
}

inline fun <reified T : Any, reified L : LiveData<T>> LifecycleOwner.observe(
    liveData: L?,
    noinline block: (T) -> Unit
) {
    liveData?.observe(this, { it?.let(block::invoke) })
}

fun <T> MutableLiveData<T>.requireValue(messageIfNull: String = "required value was null and not set"): T {
    return this.value ?: throw IllegalStateException(messageIfNull)
}