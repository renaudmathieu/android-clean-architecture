package fr.renaudmathieu.swapi.presentation.theme;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.renaudmathieu.swapi.R;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> implements ThemeContract.AdapterView {

    private ThemePresenter mThemePresenter;

    public ThemeAdapter(ThemePresenter themePresenter) {
        mThemePresenter = themePresenter;
    }

    @Override
    public ThemeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mThemePresenter.mThemes.get(position).getKey());
        holder.mPicture.setImageURI("http://lorempixel.com/400/200/sports/" + position); //TODO: Let's use a random for now

        //TODO: I put alpha here because I've only develop FILMS
        if (!mThemePresenter.mThemes.get(position).isState()) {
            holder.mPicture.setAlpha(0.2F);
        }
    }

    @Override
    public int getItemCount() {
        return (mThemePresenter.mThemes.size());
    }

    @Override
    public void updateAdapterView() {
        notifyDataSetChanged();
    }

    /**
     * Item View Holder
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.theme_picture)
        SimpleDraweeView mPicture;

        @BindView(R.id.theme_name)
        TextView mTitle;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mThemePresenter.showCategory(mThemePresenter.mThemes.get(getAdapterPosition()).getKey());
                }
            });
        }
    }

}
