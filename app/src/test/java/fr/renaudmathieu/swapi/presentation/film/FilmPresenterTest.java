package fr.renaudmathieu.swapi.presentation.film;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import fr.renaudmathieu.swapi.data.FilmRepository;
import fr.renaudmathieu.swapi.domain.DataListBean;
import fr.renaudmathieu.swapi.domain.usecase.film.GetFilmsUseCase;
import rx.Scheduler;
import rx.Subscriber;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FilmPresenterTest {

    FilmPresenter mFilmPresenter;

    @Mock
    FilmContract.View mView;

    @Mock
    FilmContract.AdapterView mAdapterView;

    @Mock
    Scheduler mScheduler;

    @Mock
    FilmRepository mFilmRepository;

    @Spy
    GetFilmsUseCase mGetFilmsUseCase = new GetFilmsUseCase(mScheduler, mFilmRepository);

    @Captor
    ArgumentCaptor<Subscriber> mSubscriberArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        mFilmPresenter = new FilmPresenter(mGetFilmsUseCase);
        mFilmPresenter.subscribe(mView, mAdapterView);
    }

    @Test
    public void unsubscribe() throws Exception {
        mFilmPresenter.unsubscribe();

        verify(mGetFilmsUseCase).unsubscribe();
        assertNull(mFilmPresenter.mView);
        assertNull(mFilmPresenter.mAdapterView);
    }

    @Test
    public void getFilmList() throws Exception {
        doNothing().when(mGetFilmsUseCase).execute(any(Subscriber.class));

        mFilmPresenter.getFilmList();

        verify(mGetFilmsUseCase).execute(mSubscriberArgumentCaptor.capture());
        mSubscriberArgumentCaptor.getValue().onNext(mock(DataListBean.class));
        mSubscriberArgumentCaptor.getValue().onCompleted();

        assertNotNull(mFilmPresenter.mFilms);
    }
}