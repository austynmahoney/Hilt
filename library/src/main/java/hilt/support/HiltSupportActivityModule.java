package hilt.support;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
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
public class HiltSupportActivityModule {

    private final ActionBarActivity mActivity;

    public HiltSupportActivityModule(ActionBarActivity activity) {
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
}