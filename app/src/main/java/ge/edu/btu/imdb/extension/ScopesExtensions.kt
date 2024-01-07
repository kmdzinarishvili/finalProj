package ge.edu.btu.imdb.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Extension functions for simplifying common coroutine operations related to ViewModels and Fragments.
 */


/**
 * Launches a coroutine within the ViewModel's scope with the provided coroutine context.
 *
 * @param coroutineContext The coroutine context in which the coroutine should run. Default is Dispatchers.IO.
 * @param block The suspend block of code to be executed within the coroutine.
 */

fun ViewModel.viewModelScope(
    coroutineContext: CoroutineContext = Dispatchers.IO,
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(coroutineContext) {
        block()
    }
}

/**
 * Launches a coroutine within the fragment's lifecycle scope, with the provided coroutine context,
 * and repeats the execution when the lifecycle state changes to the specified state.
 *
 * @param coroutineContext The coroutine context in which the coroutine should run. Default is Dispatchers.Main.
 * @param lifecycleState The desired lifecycle state at which to start and repeat the coroutine. Default is RESUMED.
 * @param block The suspend block of code to be executed within the coroutine.
 */
fun Fragment.lifecycleScope(
    coroutineContext: CoroutineContext = Dispatchers.Main,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: suspend CoroutineScope.() -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch(coroutineContext) {
        repeatOnLifecycle(lifecycleState) {
            this.block()
        }
    }
}