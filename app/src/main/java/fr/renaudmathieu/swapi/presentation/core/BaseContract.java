package fr.renaudmathieu.swapi.presentation.core;


import android.os.Bundle;

public interface BaseContract {

    interface AdapterView {

        void updateAdapterView();
    }

    interface View {

    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void onRestore(Bundle bundle);

        void onSaved(Bundle bundle);
    }
}
