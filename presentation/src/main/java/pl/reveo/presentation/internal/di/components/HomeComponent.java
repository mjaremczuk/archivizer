package pl.reveo.presentation.internal.di.components;

import dagger.Component;
import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.internal.di.modules.ActivityModule;
import pl.reveo.presentation.internal.di.modules.HomeModule;
import pl.reveo.presentation.view.activity.HomeActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class, HomeModule.class })
public interface HomeComponent extends ActivityComponent {

    void inject(HomeActivity activity);
}
