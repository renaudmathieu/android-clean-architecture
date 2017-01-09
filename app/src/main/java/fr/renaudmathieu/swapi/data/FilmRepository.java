package fr.renaudmathieu.swapi.data;


import javax.inject.Inject;

import fr.renaudmathieu.swapi.domain.DataListBean;
import fr.renaudmathieu.swapi.domain.entities.FilmModel;
import retrofit2.Retrofit;
import rx.Observable;

public class FilmRepository {

    Retrofit mRetrofit;

    @Inject
    public FilmRepository(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public Observable<DataListBean<FilmModel>> getFilms() {
        FilmService service = mRetrofit.create(FilmService.class);
        return service.getFilms();
    }

}
