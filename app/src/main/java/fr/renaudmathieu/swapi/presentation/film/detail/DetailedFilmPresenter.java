package fr.renaudmathieu.swapi.presentation.film.detail;


import android.util.Log;

import javax.inject.Inject;

import fr.renaudmathieu.swapi.domain.entities.PeopleModel;
import fr.renaudmathieu.swapi.domain.usecase.people.GetCharacterUseCase;
import fr.renaudmathieu.swapi.presentation.core.BaseContract;
import fr.renaudmathieu.swapi.presentation.core.BasePresenter;
import rx.Subscriber;

public class DetailedFilmPresenter extends BasePresenter implements DetailedFilmContract.Presenter {

    DetailedFilmContract.View mView;

    GetCharacterUseCase mGetCharacterUseCase;

    @Inject
    public DetailedFilmPresenter(GetCharacterUseCase getCharacterUseCase) {
        mGetCharacterUseCase = getCharacterUseCase;
    }

    @Override
    public void getCharacter(String id) {
        mGetCharacterUseCase.setId(id);
        mGetCharacterUseCase.execute(new CharacterSubscriber());
    }

    @Override
    public void subscribe(BaseContract.View view) {
        mView = (DetailedFilmContract.View) view;
    }

    @Override
    public void unsubscribe() {
        mGetCharacterUseCase.unsubscribe();
        mView = null;
    }

    /**
     *
     */
    private class CharacterSubscriber extends Subscriber<PeopleModel> {

        @Override
        public void onNext(PeopleModel bean) {
            mView.showCharacter(bean);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("SWAPI", "onError:", e);
        }
    }
}
