package fr.renaudmathieu.swapi.domain.usecase.film;


import fr.renaudmathieu.swapi.data.FilmRepository;
import fr.renaudmathieu.swapi.domain.usecase.UseCase;
import rx.Observable;
import rx.Scheduler;

public class GetFilmsUseCase extends UseCase {

    private FilmRepository mFilmRepository;

    public GetFilmsUseCase(Scheduler postExecutionThread, FilmRepository filmRepository) {
        super(postExecutionThread);
        mFilmRepository = filmRepository;
    }

    @Override
    protected Observable buildObservable() {
        return mFilmRepository.getFilms();
    }
}
