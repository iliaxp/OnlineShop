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
import com.mahdizaredev.onlineshop.repositories.customer.UserRepository
import com.mahdizaredev.onlineshop.repositories.invoices.InvoiceRepository
import com.mahdizaredev.onlineshop.repositories.invoices.TransactionRepository
import com.mahdizaredev.onlineshop.repositories.products.ColorRepository
import com.mahdizaredev.onlineshop.repositories.products.ProductCategoryRepository
import com.mahdizaredev.onlineshop.repositories.products.ProductRepository
import com.mahdizaredev.onlineshop.repositories.site.BlogRepository
import com.mahdizaredev.onlineshop.repositories.site.ContentRepository
import com.mahdizaredev.onlineshop.repositories.site.SliderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideInvoiceRepository(api: InvoiceApi) = InvoiceRepository(api)

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi) = UserRepository(api)

    @Provides
    @Singleton
    fun provideSliderRepository(api: SliderApi) = SliderRepository(api)

    @Provides
    @Singleton
    fun provideBlogRepository(api: BlogApi) = BlogRepository(api)

    @Provides
    @Singleton
    fun provideContentRepository(api: ContentApi) = ContentRepository(api)

    @Provides
    @Singleton
    fun provideTransactionRepository(api: TransactionApi) = TransactionRepository(api)

    @Provides
    @Singleton
    fun provideColorRepository(api: ColorApi) = ColorRepository(api)

    @Provides
    @Singleton
    fun provideProductCategoryRepository(api: ProductCategoryApi) = ProductCategoryRepository(api)

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi) = ProductRepository(api)


}