package com.mahdizaredev.onlineshop.modules

import com.mahdizaredev.onlineshop.api.customer.UserApi
import com.mahdizaredev.onlineshop.api.invoices.InvoiceApi
import com.mahdizaredev.onlineshop.api.invoices.TransactionApi
import com.mahdizaredev.onlineshop.api.products.ColorApi
import com.mahdizaredev.onlineshop.api.products.ProductApi
import com.mahdizaredev.onlineshop.api.products.ProductCategoryApi
import com.mahdizaredev.onlineshop.api.site.BlogApi
import com.mahdizaredev.onlineshop.api.site.ContentApi
import com.mahdizaredev.onlineshop.api.site.SliderApi
import com.mahdizaredev.onlineshop.config.UnsafeSSLConfig
import com.mahdizaredev.onlineshop.models.invoices.Transaction
import com.mahdizaredev.onlineshop.models.products.ProductColor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideApi(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://onlineshop.holosen.net/")
            .client(UnsafeSSLConfig.unsafeOkHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return provideApi().create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInvoiceApi(): InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(): TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideColorApi(): ColorApi {
        return provideApi().create(ColorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return provideApi().create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductCategoryApi(): ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }
    @Provides
    @Singleton
    fun provideBlogApi(): BlogApi {
        return provideApi().create(BlogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContentApi(): ContentApi {
        return provideApi().create(ContentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSliderApi(): SliderApi {
        return provideApi().create(SliderApi::class.java)
    }

}