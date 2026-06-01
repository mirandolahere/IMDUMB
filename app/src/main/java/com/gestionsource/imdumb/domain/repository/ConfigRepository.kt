package com.gestionsource.imdumb.domain.repository

import com.gestionsource.imdumb.domain.model.AppConfig
import io.reactivex.Completable
import io.reactivex.Single

interface ConfigRepository {

    fun fetchRemoteConfig(): Single<AppConfig>

    fun saveLocalConfig(config: AppConfig): Completable

    fun getLocalConfig(): Single<AppConfig>
}