package fr.renaudmathieu.swapi.data;


import javax.inject.Inject;
import javax.inject.Singleton;

import fr.renaudmathieu.swapi.domain.DataListBean;
import fr.renaudmathieu.swapi.domain.entities.PeopleModel;
import retrofit2.Retrofit;
import rx.Observable;

@Singleton
public class PeopleRepository {

    Retrofit mRetrofit;

    @Inject
    public PeopleRepository(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public Observable<DataListBean<PeopleModel>> getPeople() {
        PeopleService service = mRetrofit.create(PeopleService.class);
        return service.getPeople();
    }

    public Observable<PeopleModel> getCharacter(String id) {
        PeopleService service = mRetrofit.create(PeopleService.class);
        return service.getCharacter(id);
    }

}