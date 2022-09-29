package br.com.dionataferraz.vendas.account.data.remote

import br.com.dionataferraz.vendas.BuildConfig
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val DURATION = 60L
class RetrofitNetworkClient {

    fun createNetworkClient(baseUrl: String = BuildConfig.HTTP_SERVER) =
        retrofitClient(
            baseUrl = baseUrl,
            httpClient = httpClient(),
            moshiConverterFactory = moshi(),
        )

    private fun moshi() = MoshiConverterFactory.create(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    private fun httpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loginInterceptor())
            .connectTimeout(br.com.dionataferraz.vendas.account.data.remote.DURATION, TimeUnit.SECONDS)
            .readTimeout(br.com.dionataferraz.vendas.account.data.remote.DURATION, TimeUnit.SECONDS)
            .writeTimeout(br.com.dionataferraz.vendas.account.data.remote.DURATION, TimeUnit.SECONDS)
            .build()

    private fun loginInterceptor() =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    private fun retrofitClient(
        baseUrl: String,
        httpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }
}