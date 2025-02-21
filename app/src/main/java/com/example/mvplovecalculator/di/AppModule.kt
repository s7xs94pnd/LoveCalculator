package com.example.mvplovecalculator.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.mvplovecalculator.BuildConfig
import com.example.mvplovecalculator.data.database.AppDatabase
import com.example.mvplovecalculator.data.database.dao.LoveResultDao
import com.example.mvplovecalculator.data.network.ApiService
import com.example.mvplovecalculator.data.repository.LoveCalculatorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@HiltAndroidApp
class AppModule:Application(){
    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule
    {
        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    object RepositoryModule {

        @Provides
        fun provideLoveCalculatorRepository(apiService: ApiService): LoveCalculatorRepository {
            return LoveCalculatorRepository(apiService)
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object RoomModule {

        @Provides
        @Singleton
        fun provideDatabase(app: Application): AppDatabase {
            return Room.databaseBuilder(app, AppDatabase::class.java, "app_database")
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        @Singleton
        fun provideCalculatedResultDao(database: AppDatabase): LoveResultDao {
            return database.calculatedResultDao()
        }
    }


    @Module
    @InstallIn(SingletonComponent::class)
    object SharedPreferencesModule {

        @Provides
        @Singleton
        fun provideSharedPreferences(app: Application): SharedPreferences {
            return app.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        }

        @Provides
        @Singleton
        fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
            return sharedPreferences.edit()
        }
    }
}