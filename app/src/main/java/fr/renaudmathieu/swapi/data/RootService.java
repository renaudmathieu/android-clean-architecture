package fr.renaudmathieu.swapi.data;

import fr.renaudmathieu.swapi.domain.entities.RootModel;
import retrofit2.http.GET;
import rx.Observable;

public interface RootService {

    @GET("api/")
    Observable<RootModel> getRoot();

}
