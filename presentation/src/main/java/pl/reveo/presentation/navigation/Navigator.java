package pl.reveo.presentation.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.reveo.presentation.view.activity.ContactDetailsActivity;

/**
 * Navigation handler
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {

    }

    public void navigateToContactDetails(Activity context,String lookupKey){
        Bundle extraBundle = new Bundle();
        extraBundle.putString(ContactDetailsActivity.EXTRA_CONTACT_LOOK_UP_KEY,lookupKey);
        navigateToActivity(context, ContactDetailsActivity.class,extraBundle,null);
    }

    private void navigateToActivity(Activity context, Class activityClass, Bundle extras, Bundle optionsData) {
        if (context != null) {
            Intent intent = new Intent(context, activityClass);
            if (extras != null) {
                intent.putExtras(extras);
            }
            ActivityCompat.startActivity(context,intent,optionsData);
        }
    }
}
