package pl.reveo.presentation.internal.di.modules;

import android.content.ContentResolver;
import android.content.Context;
import android.support.v4.app.LoaderManager;

import dagger.Module;
import dagger.Provides;
import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.presenter.HomePresenter;
import pl.reveo.presentation.view.adapter.ContactAdapter;

@Module
public class HomeModule {

    public HomeModule() {

    }

    @PerActivity
    @Provides
    HomePresenter provideHomePrepsenter(Context context, ContentResolver contentResolver, LoaderManager loaderManager) {
        return new HomePresenter(context,contentResolver,loaderManager);
    }

    @PerActivity
    @Provides
    ContactAdapter provideContactAdapter() {
        return new ContactAdapter();
    }
}
