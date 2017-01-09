package fr.renaudmathieu.swapi.presentation.film.detail;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.renaudmathieu.swapi.R;
import fr.renaudmathieu.swapi.domain.entities.FilmModel;
import fr.renaudmathieu.swapi.domain.entities.PeopleModel;
import fr.renaudmathieu.swapi.presentation.film.FilmActivity;

@FragmentWithArgs
public class DetailedFilmFragment extends DialogFragment implements DetailedFilmContract.View {

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.released)
    TextView mReleased;

    @BindView(R.id.producer)
    TextView mProducer;

    @BindView(R.id.director)
    TextView mDirector;

    @BindView(R.id.opening)
    TextView mOpening;

    @BindView(R.id.people)
    TextView mPeople;

    @Inject
    DetailedFilmPresenter mDetailedFilmPresenter;

    @Arg
    FilmModel mFilm;

    private static String getIdFromURL(final String url) {
        return url.replaceFirst(".*/([^/?]+).*", "$1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed_film, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((FilmActivity) getActivity()).getGuestComponent().inject(this);
        mDetailedFilmPresenter.subscribe(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentArgs.inject(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        mTitle.setText(mFilm.getTitle());
        mReleased.setText(mFilm.getReleaseDate());
        mProducer.setText(String.format("Producer: %s", mFilm.getProducer()));
        mDirector.setText(String.format("Director: %s", mFilm.getDirector()));
        mOpening.setText(mFilm.getDescription());

        //TODO: QUICK AND DIRTY
        if (mFilm.getPeopleList().size() >= 3) {
            for (int i = 0; i < 3; i++) {
                final String id = getIdFromURL(mFilm.getPeopleList().get(i));
                Log.w("SWAPI", "onStart: " + id);
                mDetailedFilmPresenter.getCharacter(id);
            }
        }
    }

    @Override
    public void onResume() {
        // Force the DialogFragment to match_parent width
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDetailedFilmPresenter.unsubscribe();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showCharacter(PeopleModel people) {
        mPeople.setVisibility(View.VISIBLE);
        mPeople.setText(people.getName());
    }

}
