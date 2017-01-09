package fr.renaudmathieu.swapi.data;


import fr.renaudmathieu.swapi.domain.DataListBean;
import fr.renaudmathieu.swapi.domain.entities.PeopleModel;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface PeopleService {

    @GET("api/people")
    Observable<DataListBean<PeopleModel>> getPeople();

    @GET("api/people/{id}")
    Observable<PeopleModel> getCharacter(@Path("id") String id);
}
