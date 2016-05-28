package pl.reveo.presentation;

import android.app.Application;

import pl.reveo.presentation.internal.di.components.ApplicationComponent;
import pl.reveo.presentation.internal.di.components.DaggerApplicationComponent;
import pl.reveo.presentation.internal.di.modules.ApplicationModule;


/**
 * Android application
 */
public class AndroidApplication extends Application {
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }


    void init(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
