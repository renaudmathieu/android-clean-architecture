package fr.renaudmathieu.swapi.presentation.film;


import fr.renaudmathieu.swapi.domain.entities.FilmModel;
import fr.renaudmathieu.swapi.presentation.core.BaseContract;

public class FilmContract {

    interface AdapterView extends BaseContract.AdapterView {

    }

    interface View extends BaseContract.View {

        void showError();

        void showFilm(FilmModel filmModel);
    }

    interface Presenter extends BaseContract.Presenter {

        void subscribe(BaseContract.View view, FilmContract.AdapterView adapterView);

        void getFilmList();

        void showFilm(FilmModel filmModel);
    }
}
