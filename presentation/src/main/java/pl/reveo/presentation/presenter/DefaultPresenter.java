package pl.reveo.presentation.presenter;

import android.support.annotation.CallSuper;

import javax.inject.Inject;

import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.executor.ThreadExecutor;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 *Default presenter implementation
 */
public abstract class DefaultPresenter<D> implements Presenter<D> {

    D dataView;
    protected CompositeSubscription compositeSubscription = new CompositeSubscription();
    @Inject
    PostExecutionThread postExecutionThread;
    @Inject
    ThreadExecutor threadExecutor;


    public DefaultPresenter(){

    }


    @Override
    public void resume() {

    }


    @Override
    public void destroy() {
        compositeSubscription.unsubscribe();
        compositeSubscription = new CompositeSubscription();
    }

    @CallSuper
    public void setDataView(D d) {
        this.dataView = d;
    }

    public void subscribeObserveAndRegisterSubscriber(Observable observable, Subscriber subscriber){
        observable.subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        compositeSubscription.add(observable.subscribe(subscriber));
    }
}
