package fr.renaudmathieu.swapi;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

import fr.renaudmathieu.swapi.dagger.component.ApplicationComponent;

public class AndroidApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = initializeApplicationComponent();
        mApplicationComponent.inject(this);

        // Initialize Third-party libraries
        initializeStetho();
        initializeFresco();
    }

    ApplicationComponent initializeApplicationComponent() {
        return ApplicationComponent.Initializer.init(getApplicationContext());
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    void initializeStetho() {
        Stetho.initializeWithDefaults(this);
    }

    void initializeFresco() {
        Fresco.initialize(this);
    }
}
