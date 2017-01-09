package fr.renaudmathieu.swapi;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.renaudmathieu.swapi.presentation.film.FilmActivity;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    public void films(Activity activity) {
        if (activity != null) {
            Intent intentToLaunch = FilmActivity.getCallingIntent(activity);
            activity.startActivity(intentToLaunch);
            activity.overridePendingTransition(0, 0);
        }
    }
}
