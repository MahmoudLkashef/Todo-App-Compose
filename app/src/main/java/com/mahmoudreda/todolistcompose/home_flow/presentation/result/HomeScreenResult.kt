package com.mahmoudreda.todolistcompose.home_flow.presentation.result
import com.mahmoudreda.todolistcompose.core.base.Result
import com.mahmoudreda.todolistcompose.home_flow.presentation.viewstate.HomeScreenViewState


sealed class HomeScreenResult:Result<HomeScreenViewState>{

    data class GetPosts(val viewState: HomeScreenViewState): HomeScreenResult(){
        override fun reduce(
            defaultState: HomeScreenViewState,
            oldState: HomeScreenViewState
        ): HomeScreenViewState {
            return viewState.copy()
        }
    }
/*
    data class GetPostsByTitle(val viewState: HomeScreenViewState): HomeScreenResult(){
        override fun reduce(
            defaultState: HomeScreenViewState,
            oldState: HomeScreenViewState
        ): HomeScreenViewState {
            return viewState.copy()
        }
    }*/


}
