package fr.renaudmathieu.swapi.dagger.component;


import android.app.Activity;

import dagger.Component;
import fr.renaudmathieu.swapi.dagger.module.ActivityModule;
import fr.renaudmathieu.swapi.dagger.scope.PerActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    /*
    *  Expose those object to sub-graphs
    */
    Activity activity();
}
