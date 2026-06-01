package com.gestionsource.imdumb.data.repository

import android.content.SharedPreferences
import com.gestionsource.imdumb.domain.model.AppConfig
import com.gestionsource.imdumb.domain.repository.ConfigRepository
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ConfigRepositoryImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
    private val sharedPreferences: SharedPreferences
) : ConfigRepository {

    override fun fetchRemoteConfig(): Single<AppConfig> {
        return Single.create { emitter ->

            remoteConfig.setConfigSettingsAsync(
                FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(0)
                    .build()
            )

            remoteConfig.setDefaultsAsync(
                mapOf(
                    KEY_WELCOME_TEXT to "Bienvenido a IMDUMB",
                    KEY_RECOMMENDATIONS_ENABLED to true
                )
            )

            remoteConfig.fetchAndActivate()
                .addOnSuccessListener {
                    val config = AppConfig(
                        welcome_text = remoteConfig.getString(KEY_WELCOME_TEXT),
                        recommendationsEnabled =
                        remoteConfig.getBoolean(KEY_RECOMMENDATIONS_ENABLED)
                    )
                    emitter.onSuccess(config)
                }
                .addOnFailureListener { error ->
                    emitter.onError(error)
                }
        }
    }

    override fun saveLocalConfig(config: AppConfig): Completable {
        return Completable.fromAction {
            sharedPreferences.edit()
                .putString(KEY_WELCOME_TEXT, config.welcome_text)
                .putBoolean(KEY_RECOMMENDATIONS_ENABLED, config.recommendationsEnabled)
                .apply()
        }
    }

    override fun getLocalConfig(): Single<AppConfig> {
        return Single.fromCallable {
            AppConfig(
                welcome_text = sharedPreferences.getString(KEY_WELCOME_TEXT, "Bienvenido a IMDUMB")
                    ?: "Bienvenido a IMDUMB",
                recommendationsEnabled = sharedPreferences.getBoolean(
                    KEY_RECOMMENDATIONS_ENABLED,
                    true
                )
            )
        }
    }

    companion object {
        private const val KEY_WELCOME_TEXT = "welcome_text"
        private const val KEY_RECOMMENDATIONS_ENABLED = "feature_recommendations_enabled"
    }
}