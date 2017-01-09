package fr.renaudmathieu.swapi.data;


import javax.inject.Inject;
import javax.inject.Singleton;

import fr.renaudmathieu.swapi.domain.entities.RootModel;
import retrofit2.Retrofit;
import rx.Observable;

@Singleton
public class RootRepository {

    Retrofit mRetrofit;

    @Inject
    public RootRepository(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public Observable<RootModel> getRoot() {
        RootService service = mRetrofit.create(RootService.class);
        return service.getRoot();
    }

}
