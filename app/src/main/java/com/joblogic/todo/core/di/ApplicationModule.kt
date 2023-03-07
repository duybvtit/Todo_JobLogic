package com.joblogic.todo.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.joblogic.todo.AndroidApplication
import com.joblogic.todo.R
import com.joblogic.todo.core.functional.FlowCallAdapterFactory
import com.joblogic.todo.data.cache.Keys
import com.joblogic.todo.data.database.ProductDao
import com.joblogic.todo.data.database.TodoDatabase
import com.joblogic.todo.data.repositories.ProductRepository
import com.joblogic.todo.domain.network.ServiceInterceptor
import com.joblogic.todo.domain.repositories.IProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun providesMainApplicationInstance(@ApplicationContext context: Context): AndroidApplication {
        return context as AndroidApplication
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Keys.baseUrl())
            .client(createClient())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideSharePreference(application: Application): SharedPreferences {
        return application.getSharedPreferences(
            application.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )
    }


    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(
            ServiceInterceptor()
        )
        if (com.joblogic.todo.BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    internal fun providesTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room
            .databaseBuilder(context, TodoDatabase::class.java, TodoDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDao(db: TodoDatabase): ProductDao {
        return db.productDao()
    }

    @Provides
    @Singleton
    fun provideProductRepository(dataSource: ProductRepository): IProductRepository = dataSource
}
