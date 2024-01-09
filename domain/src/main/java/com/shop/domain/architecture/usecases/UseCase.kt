package com.shop.domain.architecture.usecases

import com.shop.domain.architecture.Events
import kotlinx.coroutines.flow.Flow

interface UseCase<I, O> {
    operator fun invoke(parameter: I): Flow<Events<O>>
}
