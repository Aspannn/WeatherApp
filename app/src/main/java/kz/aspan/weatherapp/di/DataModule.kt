package kz.aspan.weatherapp.di

import android.app.Application
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import kz.aspan.weatherapp.data.database.room.WeatherRoomDatabase
import kz.aspan.weatherapp.data.database.room.dao.WeatherDao
import kz.aspan.weatherapp.data.network.api.WeatherApi
import kz.aspan.weatherapp.data.network.interceptor.ApiKeyInterceptor
import kz.aspan.weatherapp.data.repository.WeatherRepositoryImpl
import kz.aspan.weatherapp.domain.repository.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideConvertorFactory() = GsonConverterFactory.create()

        @ApplicationScope
        @Provides
        fun provideLoginInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @ApplicationScope
        @Provides
        fun provideOkHttp(
            loggingInterceptor: HttpLoggingInterceptor,
            apiKeyInterceptor: ApiKeyInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(apiKeyInterceptor)
                .build()
        }

        @ApplicationScope
        @Provides
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com")
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .build()

        }

        @Provides
        @ApplicationScope
        fun provideWeatherApi(
            retrofit: Retrofit
        ): WeatherApi {
            return retrofit.create(WeatherApi::class.java)
        }

        @Provides
        @ApplicationScope
        fun provideRoom(application: Application): WeatherRoomDatabase {
            return Room
                .databaseBuilder(
                    application,
                    WeatherRoomDatabase::class.java,
                    name = "WeatherRoomDatabase"
                )
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        @ApplicationScope
        fun provideWeatherDao(database: WeatherRoomDatabase): WeatherDao {
            return database.weatherDao()
        }

    }
}