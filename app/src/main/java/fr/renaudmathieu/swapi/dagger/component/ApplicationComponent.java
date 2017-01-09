package fr.renaudmathieu.swapi.dagger.component;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import fr.renaudmathieu.swapi.AndroidApplication;
import fr.renaudmathieu.swapi.Navigator;
import fr.renaudmathieu.swapi.dagger.module.ApplicationModule;
import fr.renaudmathieu.swapi.dagger.module.NetModule;
import fr.renaudmathieu.swapi.data.FilmRepository;
import fr.renaudmathieu.swapi.data.PeopleRepository;
import fr.renaudmathieu.swapi.data.RootRepository;
import fr.renaudmathieu.swapi.presentation.core.BaseActivity;
import rx.Scheduler;

@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {

    void inject(AndroidApplication androidApplication);

    void inject(BaseActivity activity);

    /*
     *  Expose those object to sub-graphs
     */

    Context applicationContext();

    Navigator navigator();

    Scheduler scheduler();

    Gson gson();

    RootRepository rootRepository();

    FilmRepository filmRepository();

    PeopleRepository peopleRepository();

    /*
     *  Component Initializer
     */

    final class Initializer {

        private Initializer() {
            throw new UnsupportedOperationException();
        }

        public static ApplicationComponent init(Context context) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(context))
                    .netModule(new NetModule())
                    .build();
        }
    }

}
