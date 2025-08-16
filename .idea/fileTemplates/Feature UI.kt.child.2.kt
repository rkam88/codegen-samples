package ${PACKAGE_NAME}

import arrow.core.Either
import javax.inject.Inject

internal class ${NAME}UiConverter @Inject constructor() {

    // TODO: Update input parameter type
    fun convert(response: Either<Exception, Any>): ${NAME}.UiState {
        return response.fold(
            ifLeft = { ${NAME}.UiState.Error(it) },
            ifRight = { ${NAME}.UiState.Content() }// TODO: Implement conversion
        )
    }

}
