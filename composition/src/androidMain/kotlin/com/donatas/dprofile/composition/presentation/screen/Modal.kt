package com.donatas.dprofile.composition.presentation.screen

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.presentation.navigation.MainNavGraph
import com.donatas.dprofile.composition.presentation.navigation.ModalState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet
import org.koin.androidx.compose.get

internal const val modalRoute: String = "modal"

@Composable
@Destination(route = modalRoute, style = DestinationStyleBottomSheet::class)
@MainNavGraph
internal fun Modal(state: ModalState = get()) {
    state.getModal()?.Compose()
}
