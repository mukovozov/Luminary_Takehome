package cvdevelopers.takehome.dagger.client_list

import cvdevelopers.takehome.dagger.app.ApplicationComponent
import cvdevelopers.takehome.dagger.scope.FeatureScope
import cvdevelopers.takehome.ui.client_list.ClientListFragment
import dagger.Component


@FeatureScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ClientListModule::class]
)
interface ClientListComponent {
    fun inject(target: ClientListFragment)

    class Initializer private constructor() {
        companion object {
            fun init(appComponent: ApplicationComponent): ClientListComponent {
                return DaggerClientListComponent.builder()
                    .applicationComponent(appComponent)
                    .build()
            }
        }
    }
}