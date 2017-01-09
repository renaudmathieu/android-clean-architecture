package fr.renaudmathieu.swapi.presentation.theme;


import fr.renaudmathieu.swapi.presentation.core.BaseContract;

public class ThemeContract {

    interface AdapterView extends BaseContract.AdapterView {

    }

    interface View extends BaseContract.View {

        void showError();

        void showCategory(String key);
    }

    interface Presenter extends BaseContract.Presenter {

        void subscribe(BaseContract.View view, ThemeContract.AdapterView adapterView);

        void getRootList();

        void showCategory(String key);
    }
}
