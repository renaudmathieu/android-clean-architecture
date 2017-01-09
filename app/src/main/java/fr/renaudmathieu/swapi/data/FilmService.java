package fr.renaudmathieu.swapi.data;


import fr.renaudmathieu.swapi.domain.DataListBean;
import fr.renaudmathieu.swapi.domain.entities.FilmModel;
import retrofit2.http.GET;
import rx.Observable;

public interface FilmService {

    @GET("api/films")
    Observable<DataListBean<FilmModel>> getFilms();
}
