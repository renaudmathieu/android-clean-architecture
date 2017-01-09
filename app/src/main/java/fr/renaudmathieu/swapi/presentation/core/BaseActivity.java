package fr.renaudmathieu.swapi.presentation.core;


import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import fr.renaudmathieu.swapi.AndroidApplication;
import fr.renaudmathieu.swapi.Navigator;
import fr.renaudmathieu.swapi.R;
import fr.renaudmathieu.swapi.dagger.component.ApplicationComponent;
import fr.renaudmathieu.swapi.dagger.component.GuestComponent;
import fr.renaudmathieu.swapi.dagger.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Inject
    protected Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
    }

    protected void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, 0);
        ft.replace(R.id.content, fragment).commit();
    }

    public void setToolbarVisibility(int visibility) {
        if (mToolbar != null) {
            mToolbar.setVisibility(visibility);
        }
    }

    public void setToolbarTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    public void setToolbarTitle(int resId) {
        if (mToolbar != null) {
            mToolbar.setTitle(resId);
        }
    }

    public void setToolbarArrowColor(int color) {
        if (mToolbar != null && getSupportActionBar() != null) {
            final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_black_24dp);
            upArrow.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public abstract GuestComponent getGuestComponent();
}
