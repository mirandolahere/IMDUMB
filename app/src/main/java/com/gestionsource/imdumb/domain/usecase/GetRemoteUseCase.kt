package com.gestionsource.imdumb.domain.usecase

import com.gestionsource.imdumb.domain.model.AppConfig
import com.gestionsource.imdumb.domain.repository.ConfigRepository
import io.reactivex.Single
import javax.inject.Inject

class GetRemoteConfigUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {
    fun execute(): Single<AppConfig> {
        return configRepository.fetchRemoteConfig()
    }
}