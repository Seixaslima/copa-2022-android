package me.dio.copa.catar.domain.usecase

import me.dio.copa.catar.domain.repositories.MatchesRepository

class EnableNotificationUseCase constructor(
    private val repository: MatchesRepository
) {
    suspend operator fun invoke(id: String) = repository.enableNotificationFor(id)
}