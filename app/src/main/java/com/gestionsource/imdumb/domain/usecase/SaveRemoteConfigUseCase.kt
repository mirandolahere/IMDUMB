package com.gestionsource.imdumb.domain.usecase

import com.gestionsource.imdumb.domain.model.AppConfig
import com.gestionsource.imdumb.domain.repository.ConfigRepository
import io.reactivex.Completable
import javax.inject.Inject

class SaveLocalConfigUseCase @Inject constructor(
    private val configRepository: ConfigRepository
) {
    fun execute(config: AppConfig): Completable {
        return configRepository.saveLocalConfig(config)
    }
}