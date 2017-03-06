package com.damidev.core.retain;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

public class RetainFragmentHelper {

    private static final String RETAIN_FRAGMENT_TAG = "retain_fragment";

    /**
     * @return @Nullable RetainFragment. Keep in mind, even if fragment has been set, system may
     * remove it from memory at arbitrary time.
     */
    @Nullable
    private static <T> RetainFragment<T> getInstance(FragmentManager fragmentManager, String tag) {
        //noinspection unchecked
        return (RetainFragment<T>) fragmentManager.findFragmentByTag(tag);
    }

    /**
     * @return get Object or null if fragment has been cleaned up.
     */
    @Nullable
    public static <T> T getObjectOrNull(@NonNull Object tag,
                                        @NonNull FragmentManager fragmentManager) {
        final RetainFragment<T> instance = RetainFragmentHelper.getInstance(fragmentManager, getTag(tag));
        if (instance == null) {
            return null;
        }

        return checkNotNull(instance.getObject());
    }

    public static <T> void setObject(@NonNull Object tag,
                                     @NonNull FragmentManager fragmentManager,
                                     @NonNull T object) {
        RetainFragment<T> instance = getInstance(fragmentManager, getTag(tag));
        if (instance == null) {
            instance = RetainFragment.newInstance();
            fragmentManager
                    .beginTransaction()
                    .add(instance, getTag(tag))
                    .commitAllowingStateLoss();
        }
        instance.setObject(object);
    }

    public static <T> void clear(@NonNull Object tag, @NonNull FragmentManager fragmentManager) {
        RetainFragment<T> instance = getInstance(fragmentManager, getTag(tag));
        if (instance != null) {
            instance.setObject(null);
            fragmentManager
                    .beginTransaction()
                    .remove(fragmentManager.findFragmentByTag(getTag(tag)))
                    .commitAllowingStateLoss();
        }
    }

    @NonNull
    private static String getTag(@NonNull Object object) {
        return RETAIN_FRAGMENT_TAG + object.getClass().getCanonicalName();
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    @Nullable
    public static boolean isNotNull(@NonNull Object tag,
                                        @NonNull FragmentManager fragmentManager) {
        return RetainFragmentHelper.getInstance(fragmentManager, getTag(tag)) != null;
    }
}
