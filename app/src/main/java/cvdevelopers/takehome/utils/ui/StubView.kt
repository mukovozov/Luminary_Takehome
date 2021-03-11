package cvdevelopers.takehome.utils.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.LayoutStubViewBinding

class StubView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val EMPTY_RES_ID = -1
    }

    private var binding = LayoutStubViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        initAttributes(attrs)
    }

    private var buttonClickListener: (() -> Unit)? = null

    private fun initAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.StubView, 0, 0
            )

            val primaryTextId = typedArray.getResourceId(R.styleable.StubView_primaryText, EMPTY_RES_ID)
            val buttonTextId = typedArray.getResourceId(R.styleable.StubView_buttonText, EMPTY_RES_ID)

            createStubView(
                primaryTextId.takeIf { primaryTextId != EMPTY_RES_ID },
                buttonTextId.takeIf { buttonTextId != EMPTY_RES_ID }
            )

            typedArray.recycle()
        }
    }

    fun setPrimaryText(primaryText: String) {
        with(binding.stubViewPrimaryText) {
            isVisible = true
            text = primaryText
        }
    }

    fun setActionButton(buttonText: String, buttonClickListener: (() -> Unit)?) {
        this.buttonClickListener = buttonClickListener
        with(binding.stubViewBtn) {
            isVisible = true
            text = buttonText
            setOnClickListener {
                this@StubView.buttonClickListener?.invoke()
            }
        }
    }

    fun setActionButtonClickListener(buttonClickListener: () -> Unit) {
        this.buttonClickListener = buttonClickListener
    }

    fun removeActionButton() {
        this.buttonClickListener = null
        with(binding.stubViewBtn) {
            isVisible = false
            setOnClickListener(null)
        }
    }

    private fun createStubView(primaryText: Int?, buttonText: Int?) {
        primaryText?.let { setPrimaryText(context.getString(it)) }
        buttonText?.let { setActionButton(context.getString(it), buttonClickListener) }
    }
}
