package pl.reveo.presentation.internal.di.components;

import dagger.Component;
import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.internal.di.modules.ActivityModule;
import pl.reveo.presentation.internal.di.modules.ContactDetailsModule;
import pl.reveo.presentation.view.activity.ContactDetailsActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class, ContactDetailsModule.class })
public interface ContactDetailsComponent extends ActivityComponent {

    void inject(ContactDetailsActivity activity);
}
