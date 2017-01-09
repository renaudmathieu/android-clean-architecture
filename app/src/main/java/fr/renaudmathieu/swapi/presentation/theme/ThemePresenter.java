package fr.renaudmathieu.swapi.presentation.theme;


import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import fr.renaudmathieu.swapi.domain.entities.KeyValueModel;
import fr.renaudmathieu.swapi.domain.entities.RootModel;
import fr.renaudmathieu.swapi.domain.usecase.theme.GetRootUseCase;
import fr.renaudmathieu.swapi.presentation.core.BaseContract;
import fr.renaudmathieu.swapi.presentation.core.BasePresenter;
import icepick.State;
import rx.Subscriber;

public class ThemePresenter extends BasePresenter implements ThemeContract.Presenter {

    ThemeContract.View mView;
    ThemeContract.AdapterView mAdapterView;

    GetRootUseCase mGetRootUseCase;

    @State
    ArrayList<KeyValueModel> mThemes = new ArrayList<>();

    @Inject
    public ThemePresenter(GetRootUseCase getRootUseCase) {
        mGetRootUseCase = getRootUseCase;
    }

    @Override
    public void subscribe(BaseContract.View view) {
        throw new UnsupportedOperationException("Use subscribe(BaseContract.View view, AdapterView adapterView)");
    }

    @Override
    public void subscribe(BaseContract.View view, ThemeContract.AdapterView adapterView) {
        mView = (ThemeContract.View) view;
        mAdapterView = adapterView;
    }

    @Override
    public void unsubscribe() {
        mGetRootUseCase.unsubscribe();
        mView = null;
    }


    @Override
    public void getRootList() {
        mGetRootUseCase.execute(new RootSubscriber());
    }

    @Override
    public void showCategory(String key) {
        mView.showCategory(key);
    }

    /**
     *
     */
    private class RootSubscriber extends Subscriber<RootModel> {

        @Override
        public void onNext(RootModel bean) {
            mThemes.add(new KeyValueModel("Films", bean.getFilms(), true));
            mThemes.add(new KeyValueModel("People", bean.getPeople(), false));
            mThemes.add(new KeyValueModel("Planets", bean.getPlanets(), false));
            mThemes.add(new KeyValueModel("Species", bean.getSpecies(), false));
            mThemes.add(new KeyValueModel("Starships", bean.getStarships(), false));
            mThemes.add(new KeyValueModel("Vehicles", bean.getVehicles(), false));
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
