package pl.reveo.presentation.internal.di.modules;

import android.content.ContentResolver;
import android.content.Context;
import android.support.v4.app.LoaderManager;

import dagger.Module;
import dagger.Provides;
import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.presenter.ContactDetailsPresenter;

@Module
public class ContactDetailsModule {

    public ContactDetailsModule() {

    }

    @PerActivity
    @Provides
    ContactDetailsPresenter provideContactDetailsPresenter(Context context, ContentResolver contentResolver,
                                                           LoaderManager loaderManager) {
        return new ContactDetailsPresenter(context, contentResolver, loaderManager);
    }
}
