package fr.renaudmathieu.swapi.presentation.film;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.renaudmathieu.swapi.R;
import fr.renaudmathieu.swapi.domain.entities.FilmModel;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> implements FilmContract.AdapterView {

    private FilmPresenter mFilmPresenter;
    private ArrayList<FilmModel> mDataSet = new ArrayList<>();

    public FilmAdapter(FilmPresenter filmPresenter) {
        mFilmPresenter = filmPresenter;
    }

    @Override
    public FilmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);
        return new FilmAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FilmAdapter.ViewHolder holder, int position) {
        holder.mTitle.setText(mDataSet.get(position).getTitle());
        holder.mDirector.setText(mDataSet.get(position).getDirector());
        holder.mReleaseDate.setText(mDataSet.get(position).getReleaseDate());
        holder.mPicture.setImageURI("http://lorempixel.com/400/200/city/" + position); //TODO: Let's use a random for now
    }

    @Override
    public int getItemCount() {
        return (mDataSet.size());
    }

    @Override
    public void updateAdapterView() {
        mDataSet.clear();
        mDataSet.addAll(mFilmPresenter.mFilms);
        notifyDataSetChanged();
    }

    public void animateTo(List<FilmModel> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<FilmModel> newModels) {
        for (int i = mDataSet.size() - 1; i >= 0; i--) {
            final FilmModel model = mDataSet.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<FilmModel> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final FilmModel model = newModels.get(i);
            if (!mDataSet.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<FilmModel> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final FilmModel model = newModels.get(toPosition);
            final int fromPosition = mDataSet.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public FilmModel removeItem(int position) {
        final FilmModel model = mDataSet.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, FilmModel model) {
        mDataSet.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final FilmModel model = mDataSet.remove(fromPosition);
        mDataSet.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }


    /**
     * Item View Holder
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.picture)
        SimpleDraweeView mPicture;

        @BindView(R.id.film_name)
        TextView mTitle;

        @BindView(R.id.film_director)
        TextView mDirector;

        @BindView(R.id.film_published)
        TextView mReleaseDate;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFilmPresenter.showFilm(mFilmPresenter.mFilms.get(getAdapterPosition()));
                }
            });
        }
    }
}
