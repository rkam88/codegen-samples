package ${PACKAGE_NAME}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ${NAME}Fragment : Fragment() {

    @Inject
    internal lateinit var viewModel: ${NAME}ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return content {
            val state = viewModel.state.collectAsStateWithLifecycle()

            ${NAME}Screen(
                state = state.value,
                onUiEvent = viewModel::onUiEvent
            )

            LaunchedEffect(Unit) {
                viewModel.eventFlow.collect { event ->
                    when (event) {
                        is ${NAME}.Event.NavigateBack -> findNavController().popBackStack()
                    }
                }
            }
        }
    }
}
