package fr.renaudmathieu.swapi.dagger.module;


import dagger.Module;
import dagger.Provides;
import fr.renaudmathieu.swapi.dagger.scope.PerActivity;
import fr.renaudmathieu.swapi.data.FilmRepository;
import fr.renaudmathieu.swapi.data.PeopleRepository;
import fr.renaudmathieu.swapi.data.RootRepository;
import fr.renaudmathieu.swapi.domain.usecase.film.GetFilmsUseCase;
import fr.renaudmathieu.swapi.domain.usecase.people.GetCharacterUseCase;
import fr.renaudmathieu.swapi.domain.usecase.theme.GetRootUseCase;
import rx.Scheduler;

@Module
public class GuestModule {

    public GuestModule() {

    }

    @Provides
    @PerActivity
    GetRootUseCase provideGetRootUseCase(Scheduler postExecutionThread, RootRepository rootRepository) {
        return new GetRootUseCase(postExecutionThread, rootRepository);
    }

    @Provides
    @PerActivity
    GetFilmsUseCase provideGetFilmsUseCase(Scheduler postExecutionThread, FilmRepository filmRepository) {
        return new GetFilmsUseCase(postExecutionThread, filmRepository);
    }

    @Provides
    @PerActivity
    GetCharacterUseCase provideGetCharacterUseCase(Scheduler postExecutionThread, PeopleRepository peopleRepository) {
        return new GetCharacterUseCase(postExecutionThread, peopleRepository);
    }

}