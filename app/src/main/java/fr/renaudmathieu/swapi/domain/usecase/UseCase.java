package fr.renaudmathieu.swapi.domain.usecase;


import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase {

    protected final Scheduler mPostExecutionThread;

    protected Subscription mSubscription = Subscriptions.empty();

    public UseCase(Scheduler postExecutionThread) {
        mPostExecutionThread = postExecutionThread;
    }

    protected abstract Observable buildObservable();

    public void execute(Subscriber useCaseSubscriber) {
        unsubscribe(); // unsubscribe the previous Subscription

        mSubscription = buildObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(mPostExecutionThread)
                .subscribe(useCaseSubscriber);
    }

    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
