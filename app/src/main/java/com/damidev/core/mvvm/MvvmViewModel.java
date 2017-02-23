package com.damidev.core.mvvm;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * @author Lukas Janoska
 */

public interface MvvmViewModel<V extends MvvmView> extends Observable {

    V getView();

    void viewResumed();
    void attachView(@NonNull V view);
    void detachView();

    void saveInstanceState(@NonNull Bundle outState);
    void restoreInstanceState(@NonNull Bundle savedInstanceState);
}