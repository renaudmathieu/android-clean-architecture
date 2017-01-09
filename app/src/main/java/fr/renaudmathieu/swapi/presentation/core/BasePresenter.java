package fr.renaudmathieu.swapi.presentation.core;


import android.os.Bundle;

import icepick.Icepick;

public abstract class BasePresenter implements BaseContract.Presenter {

    public void onRestore(Bundle bundle) {
        Icepick.restoreInstanceState(this, bundle);
    }

    public void onSaved(Bundle bundle) {
        Icepick.saveInstanceState(this, bundle);
    }

}
