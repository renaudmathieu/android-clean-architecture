package fr.renaudmathieu.swapi.presentation.film;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.renaudmathieu.swapi.R;
import fr.renaudmathieu.swapi.SpacesItemDecoration;
import fr.renaudmathieu.swapi.dagger.component.GuestComponent;
import fr.renaudmathieu.swapi.dagger.module.ActivityModule;
import fr.renaudmathieu.swapi.domain.entities.FilmModel;
import fr.renaudmathieu.swapi.presentation.core.BaseActivity;
import fr.renaudmathieu.swapi.presentation.film.detail.DetailedFilmFragmentBuilder;

public class FilmActivity extends BaseActivity implements FilmContract.View, SearchView.OnQueryTextListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    FilmPresenter mFilmPresenter;

    private GuestComponent mGuestComponent;
    private FilmAdapter mFilmAdapter;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, FilmActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_film);
        ButterKnife.bind(this);

        mGuestComponent = GuestComponent.Initializer.init(getApplicationComponent(), new ActivityModule(this));
        mGuestComponent.inject(this);

        setSupportActionBar(mToolbar);
        setToolbarTitle(getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setToolbarArrowColor(Color.WHITE);

        mFilmAdapter = new FilmAdapter(mFilmPresenter);
        mFilmPresenter.subscribe(this, mFilmAdapter);
        mRecyclerView.setAdapter(mFilmAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(15));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFilmPresenter.onSaved(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mFilmPresenter.getFilmList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFilmPresenter.unsubscribe();
    }

    @Override
    public GuestComponent getGuestComponent() {
        return mGuestComponent;
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error. Need to handle this.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFilm(FilmModel filmModel) {
        DialogFragment fragment = new DetailedFilmFragmentBuilder(filmModel).build();
        fragment.show(getSupportFragmentManager(), "fragment_detailed_film");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.film_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mFilmAdapter.animateTo(mFilmPresenter.filter(query));
        mRecyclerView.scrollToPosition(0);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


}
