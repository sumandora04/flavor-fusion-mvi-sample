package com.shop.domain.architecture.usecases

import com.shop.domain.architecture.Events

interface UseCase<I, O> {
    suspend operator fun invoke(parameter: I): Events<O>
}
