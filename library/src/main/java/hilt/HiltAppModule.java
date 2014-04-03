package hilt;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hilt.annotations.ForApplication;

/**
 * Provides dependencies that are alive during the whole application scope
 */
@Module(
        complete = false,
        library = true,
        injects = {})
public class HiltAppModule {

    /* package */ static Context sApplicationContext;

    public HiltAppModule(Context context) {
        sApplicationContext = context;
    }

    /**
     * Allow the application context to be injected but require that it be annotated
     * with {@link ForApplication &#64;ForApplication} to explicitly differentiate it
     * from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return sApplicationContext;
    }
}