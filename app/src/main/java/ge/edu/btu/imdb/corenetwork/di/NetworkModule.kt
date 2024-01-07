package ge.edu.btu.imdb.corenetwork.di

import ge.edu.btu.imdb.corenetwork.constants.Constants.BASE_URL
import ge.edu.btu.imdb.corenetwork.interceptor.AuthInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val networkModule = module {
    /**
     * Provides an instance of OkHttpClient configured with various interceptors.
     */
    single {
        OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(AuthInterceptor())
            .build()
    }
    /**
     * Provides an instance of Moshi JSON converter with KotlinJsonAdapterFactory.
     */
    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
    /**
     * Provides an instance of Retrofit with the configured base URL, Moshi converter, and OkHttpClient.
     */
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }
}

/**
 * Creates an instance of HttpLoggingInterceptor configured with the desired log level.
 *
 * @return The configured HttpLoggingInterceptor instance.
 */
private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BODY)
}