package com.mahmoudreda.todolistcompose.core.base

interface Intent

interface ViewState

interface Result<VS:ViewState>{
    fun reduce(defaultState: VS, oldState:VS):VS
}