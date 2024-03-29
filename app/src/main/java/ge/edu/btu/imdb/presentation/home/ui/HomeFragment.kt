package ge.edu.btu.imdb.presentation.home.ui

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import ge.edu.btu.imdb.common.extension.lifecycleScope
import ge.edu.btu.imdb.presentation.dialog.ErrorDialogFragment
import ge.edu.btu.imdb.presentation.dialog.LoadingDialogFragment
import ge.edu.btu.imdb.common.extension.viewBinding
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.presentation.enums.MovieCategory
import ge.edu.btu.imdb.presentation.custom_view.SearchCustomView
import ge.edu.btu.imdb.databinding.FragmentHomeBinding
import ge.edu.btu.imdb.presentation.home.vm.HomeViewModel
import ge.edu.btu.imdb.presentation.home.adapter.HomeAdapter
import ge.edu.btu.imdb.presentation.base.CoreBaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class HomeFragment : CoreBaseFragment<HomeViewModel>(), SearchCustomView.OnTextChangeListener {

    private lateinit var loadingDialogFragment: LoadingDialogFragment
    private lateinit var errorDialogFragment: ErrorDialogFragment

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val homeAdapter by lazy {
        HomeAdapter()
    }
    override val layout: Int
        get() = R.layout.fragment_home

    override fun onBind() {
        loadingDialogFragment = LoadingDialogFragment()
        errorDialogFragment = ErrorDialogFragment()
        binding.searchCustomView.addTextChangeListener(this)
        initObserver()
        setOnCLickListeners()
        setUpRecycler()
        setAdapterClickListeners()
        setCategoryListener()
    }

    private fun setUpRecycler() {
        binding.homeRecyclerView.adapter = homeAdapter
    }

    private fun setCategoryListener() {
        with(binding) {
            searchCustomView.setOnPopularClickListener {
                viewModel.getMovies(MovieCategory.POPULAR.category)
            }
            searchCustomView.setOnTopRatedClickListener {
                viewModel.getMovies(MovieCategory.TOP_RATED.category)
            }
        }
    }

    private fun initObserver() {
        viewModel.favoriteMovieIds
            .onEach { favoriteIds ->
                homeAdapter.setFavoriteIds(favoriteIds)
            }
            .launchIn(lifecycleScope)

        lifecycleScope {
            viewModel.movies
                .collectLatest { pagingData ->
                    loadingDialogFragment.dismissDialog()
                    homeAdapter.submitData(pagingData)
                }
        }

        lifecycleScope {
            homeAdapter.loadStateFlow.collectLatest { loadStates ->
                val isLoading = loadStates.refresh is LoadState.Loading
                if (isLoading) {
                    loadingDialogFragment.show(childFragmentManager, "LoadingDialogFragment")
                } else {
                    loadingDialogFragment.dismissDialog()
                }
                val isError = loadStates.refresh is LoadState.Error
                if (isError) {
                    showErrorDialog()
                }
            }
        }
    }

    private fun setOnCLickListeners() {
        with(binding) {
            searchCustomView.setOnFilterClickListener {
                searchCustomView.setFilterView()
            }
            searchCustomView.setOnSearchClickListener {
                searchCustomView.setTyping()
            }
            searchCustomView.setOnCancelClickListener {
                searchCustomView.cancelClicked()

            }
        }
    }

    private fun showErrorDialog() {
        errorDialogFragment.show(childFragmentManager, "ErrorDialogFragment")
        errorDialogFragment.setOnRefreshClickListener {
            errorDialogFragment.dismiss()
        }
    }

    private fun setAdapterClickListeners() {
        homeAdapter.setOnItemClickListener { item ->
            viewModel.navigateToDetail(item)
        }
        homeAdapter.setOnFavoriteClickListener { movie ->
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.manageFavoriteMovie(movie)
            }
        }
    }

    override fun onTextChange(query: String) {
        viewModel.searchMovies(query)
    }
}