package fr.renaudmathieu.swapi.presentation.film.detail;


import fr.renaudmathieu.swapi.domain.entities.PeopleModel;
import fr.renaudmathieu.swapi.presentation.core.BaseContract;

public class DetailedFilmContract {

    interface View extends BaseContract.View {

        void showError();

        void showCharacter(PeopleModel people);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCharacter(String id);

    }
}
