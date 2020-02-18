package com.app.basemodule.extensions;

import android.view.*;
import android.view.View.*;

import io.reactivex.*;
import io.reactivex.android.*;

import static com.app.basemodule.extensions.RxExtKt.*;


final public class ViewClickObservable extends Observable<Object> {
    private final View view;

    ViewClickObservable(View view) {
        this.view = view;
    }

    @Override
    protected void subscribeActual(Observer<? super Object> observer) {
        if (!checkMainThread(observer)) {
            return;
        }
        Listener listener = new Listener(view, observer);
        observer.onSubscribe(listener);
        view.setOnClickListener(listener);
    }

    static final class Listener extends MainThreadDisposable implements OnClickListener {
        private final View view;
        private final Observer<? super Object> observer;

        Listener(View view, Observer<? super Object> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override
        public void onClick(View v) {
            if (!isDisposed()) {
                observer.onNext(Notification.INSTANCE);
            }
        }

        @Override
        protected void onDispose() {
            view.setOnClickListener(null);
        }
    }

}

