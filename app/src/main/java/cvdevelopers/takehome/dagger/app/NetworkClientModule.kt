package cvdevelopers.takehome.dagger.app

import cvdevelopers.takehome.api.RandomUserApiEndpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkClientModule {

    @Provides
    @Singleton
    fun provideRandomUserEndpoint(): RandomUserApiEndpoint = Retrofit.Builder()
        .baseUrl(RandomUserApiEndpoint.SERVER)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(RandomUserApiEndpoint::class.java)
}
