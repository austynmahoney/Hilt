package hilt;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hilt.annotations.ForActivity;

/**
 * Provides dependencies that are only alive in the activity scope.
 */
@Module(
        complete = true, // Here we enable object graph validation
        library = true)
public class HiltActivityModule {

    private final HiltActivity mActivity;

    public HiltActivityModule(HiltActivity activity) {
        mActivity = activity;
    }

    /**
     * Allow the activity context to be injected but require that it be annotated with
     * {@link hilt.annotations.ForActivity &#64;ForActivity} to explicitly differentiate
     * it from an application context.
     */
    @Provides
    @Singleton
    @ForActivity
    Context providesActivityContext() {
        return mActivity;
    }

    @Provides
    @Singleton
    Activity providesActivity() {
        return mActivity;
    }

    @Provides
    @Singleton
    FragmentManager providesFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    LayoutInflater providesLayoutInflater(@ForActivity Context context) {
        return LayoutInflater.from(context);
    }

    @Provides
    @Singleton
    Resources providesResources(@ForActivity Context context) {
        return context.getResources();
    }
}