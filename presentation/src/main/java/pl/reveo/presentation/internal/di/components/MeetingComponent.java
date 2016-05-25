package pl.reveo.presentation.internal.di.components;

import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.internal.di.modules.ActivityModule;
import pl.reveo.presentation.internal.di.modules.MeetingModule;
import pl.reveo.presentation.view.screens.MeetingScreen;
import pl.reveo.presentation.view.screens.MeetingsScreen;
import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects fetchMeeting specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MeetingModule.class})
public interface MeetingComponent extends ActivityComponent {
	void inject(MeetingsScreen meetingsScreen);
	void inject(MeetingScreen meetingScreen);
}
