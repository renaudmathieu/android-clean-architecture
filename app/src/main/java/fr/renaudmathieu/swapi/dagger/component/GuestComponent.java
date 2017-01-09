package fr.renaudmathieu.swapi.dagger.component;


import dagger.Component;
import fr.renaudmathieu.swapi.dagger.module.ActivityModule;
import fr.renaudmathieu.swapi.dagger.module.GuestModule;
import fr.renaudmathieu.swapi.dagger.scope.PerActivity;
import fr.renaudmathieu.swapi.presentation.film.FilmActivity;
import fr.renaudmathieu.swapi.presentation.film.detail.DetailedFilmFragment;
import fr.renaudmathieu.swapi.presentation.theme.ThemeActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, GuestModule.class})
public interface GuestComponent extends ActivityComponent {

    void inject(ThemeActivity themeActivity);

    void inject(FilmActivity filmActivity);

    void inject(DetailedFilmFragment detailedFilmFragment);

    final class Initializer {

        private Initializer() {
            throw new UnsupportedOperationException();
        }

        public static GuestComponent init(ApplicationComponent applicationComponent, ActivityModule activityModule) {
            return DaggerGuestComponent.builder()
                    .applicationComponent(applicationComponent)
                    .activityModule(activityModule)
                    .build();
        }
    }
}
