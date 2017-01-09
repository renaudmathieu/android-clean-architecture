package fr.renaudmathieu.swapi.presentation.core;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hannesdorfmann.fragmentargs.FragmentArgs;

import javax.inject.Inject;

public class BaseFragment extends Fragment {

    @Inject
    protected Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
    }
}
