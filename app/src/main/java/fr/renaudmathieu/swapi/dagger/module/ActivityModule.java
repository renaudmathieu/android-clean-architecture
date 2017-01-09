package fr.renaudmathieu.swapi.dagger.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import fr.renaudmathieu.swapi.dagger.scope.PerActivity;

@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return mActivity;
    }
}

