package cvdevelopers.takehome.dagger.client_list

import cvdevelopers.takehome.dagger.scope.FeatureScope
import cvdevelopers.takehome.repository.ClientListRepository
import cvdevelopers.takehome.repository.ClientListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ClientListModule {

    @Binds
    @FeatureScope
    abstract fun bindUserListRepository(userListRepositoryImpl: ClientListRepositoryImpl): ClientListRepository
}