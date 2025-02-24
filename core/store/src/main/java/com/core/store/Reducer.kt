package com.core.store

interface Reducer<State, Action> {

    fun reduce(state: State, action: Action): State
}
