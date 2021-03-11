package cvdevelopers.takehome.ui.client_list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupieAdapter
import cvdevelopers.githubstalker.R
import cvdevelopers.githubstalker.databinding.FragmentClientListBinding
import cvdevelopers.takehome.dagger.client_list.ClientListComponent
import cvdevelopers.takehome.utils.lifecycle.observe
import cvdevelopers.takehome.utils.lifecycle.viewBinding
import cvdevelopers.takehome.utils.lifecycle.viewModels
import cvdevelopers.takehome.utils.ui.*
import javax.inject.Inject
import javax.inject.Provider

class ClientListFragment : BaseFragment(R.layout.fragment_client_list) {

    companion object {
        fun newInstance(): ClientListFragment {
            return ClientListFragment()
        }
    }

    @Inject
    internal lateinit var viewModelFactory: Provider<ClientListViewModel>

    private val binding by viewBinding(FragmentClientListBinding::bind)
    private val viewModel: ClientListViewModel by viewModels { viewModelFactory.get() }
    private val adapter = GroupieAdapter()

    override fun injectDependencies() {
        ClientListComponent.Initializer.init(appComponent)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

        observe(viewModel.viewState, { render(it) })
    }

    private fun initUi() = with(binding) {
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.onPulledToRefresh()
        }

        stubView.setActionButtonClickListener {
            viewModel.onRetryButtonClicked()
        }

        userList.layoutManager = LinearLayoutManager(context)
        userList.adapter = adapter
    }

    private fun render(viewState: ClientListViewState) = with(viewState.screenState) {
        when (this) {
            is Loading -> {
                binding.progressBar.isVisible = true
                binding.swipeToRefreshLayout.isVisible = false
                binding.stubView.isVisible = false
                binding.swipeToRefreshLayout.isRefreshing = false
            }
            is RefreshLoading -> {
                binding.progressBar.isVisible = false
                binding.swipeToRefreshLayout.isVisible = true
                binding.stubView.isVisible = false
                binding.swipeToRefreshLayout.isRefreshing = true
            }
            is Content -> {
                binding.progressBar.isVisible = false
                binding.swipeToRefreshLayout.isVisible = true
                binding.stubView.isVisible = false
                binding.swipeToRefreshLayout.isRefreshing = false

                content?.let(adapter::update)
            }
            is Stub -> {
                binding.progressBar.isVisible = false
                binding.swipeToRefreshLayout.isVisible = false
                binding.stubView.isVisible = true
                binding.swipeToRefreshLayout.isRefreshing = false
            }
        }
    }
}