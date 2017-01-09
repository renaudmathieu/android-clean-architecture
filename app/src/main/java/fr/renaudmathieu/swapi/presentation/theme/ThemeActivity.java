package fr.renaudmathieu.swapi.presentation.theme;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.renaudmathieu.swapi.R;
import fr.renaudmathieu.swapi.SpacesItemDecoration;
import fr.renaudmathieu.swapi.dagger.component.GuestComponent;
import fr.renaudmathieu.swapi.dagger.module.ActivityModule;
import fr.renaudmathieu.swapi.presentation.core.BaseActivity;

public class ThemeActivity extends BaseActivity implements ThemeContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    ThemePresenter mThemePresenter;

    private GuestComponent mGuestComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_theme);
        ButterKnife.bind(this);

        mGuestComponent = GuestComponent.Initializer.init(getApplicationComponent(), new ActivityModule(this));
        mGuestComponent.inject(this);

        setSupportActionBar(mToolbar);
        setToolbarTitle(getString(R.string.app_name));

        ThemeAdapter adapter = new ThemeAdapter(mThemePresenter);
        mThemePresenter.subscribe(this, adapter);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(15));
        mRecyclerView.setHasFixedSize(true);
        mThemePresenter.getRootList();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mThemePresenter.onSaved(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThemePresenter.unsubscribe();
    }

    @Override
    public GuestComponent getGuestComponent() {
        return mGuestComponent;
    }

    @Override
    public void showError() {

    }

    @Override
    public void showCategory(String key) {
        switch (key) {
            case "Films":
                mNavigator.films(this);
                break;
            default:
                Toast.makeText(this, "Premium only ^^", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
