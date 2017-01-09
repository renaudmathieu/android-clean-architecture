package fr.renaudmathieu.swapi.presentation.film;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import fr.renaudmathieu.swapi.domain.DataListBean;
import fr.renaudmathieu.swapi.domain.entities.FilmModel;
import fr.renaudmathieu.swapi.domain.usecase.film.GetFilmsUseCase;
import fr.renaudmathieu.swapi.presentation.core.BaseContract;
import fr.renaudmathieu.swapi.presentation.core.BasePresenter;
import icepick.State;
import rx.Subscriber;

public class FilmPresenter extends BasePresenter implements FilmContract.Presenter {

    FilmContract.View mView;
    FilmContract.AdapterView mAdapterView;

    GetFilmsUseCase mGetFilmsUseCase;

    @State
    ArrayList<FilmModel> mFilms = new ArrayList<>();

    @Inject
    public FilmPresenter(GetFilmsUseCase getFilmsUseCase) {
        mGetFilmsUseCase = getFilmsUseCase;
    }

    @Override
    public void subscribe(BaseContract.View view) {
        throw new UnsupportedOperationException("Use subscribe(BaseContract.View view, AdapterView adapterView)");
    }

    @Override
    public void subscribe(BaseContract.View view, FilmContract.AdapterView adapterView) {
        mView = (FilmContract.View) view;
        mAdapterView = adapterView;
    }

    @Override
    public void unsubscribe() {
        mGetFilmsUseCase.unsubscribe();
        mView = null;
        mAdapterView = null;
    }

    @Override
    public void showFilm(FilmModel filmModel) {
        mView.showFilm(filmModel);
    }

    @Override
    public void getFilmList() {
        mGetFilmsUseCase.execute(new FilmsSubscriber());
    }


    public List<FilmModel> filter(String query) {
        query = query.toLowerCase();
        ArrayList<FilmModel> filteredList = new ArrayList<>();
        for (FilmModel film : mFilms) {
            if (film.getTitle().toLowerCase().contains(query)) {
                filteredList.add(film);
            }
        }
        return filteredList;
    }

    /**
     *
     */
    private class FilmsSubscriber extends Subscriber<DataListBean<FilmModel>> {

        @Override
        public void onNext(DataListBean<FilmModel> bean) {
            mFilms.addAll(bean.getResults());
        }

        @Override
        public void onCompleted() {
            mAdapterView.updateAdapterView();
        }

        @Override
        public void onError(Throwable e) {
            Log.e("SWAPI", "onError:", e);
        }
    }
}
