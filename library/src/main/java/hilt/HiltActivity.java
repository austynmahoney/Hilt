package hilt;


import android.app.Activity;
import android.os.Bundle;

import dagger.ObjectGraph;

/**
 * Base Activity which performs injection by adding {@link #getActivityModules()}
 * to the Application object graph.
 */
public abstract class HiltActivity extends Activity implements Injector {

    private ObjectGraph mActivityGraph;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the activity graph by adding our modules into the application graph.
        HiltApplication application = (HiltApplication) getApplication();
        mActivityGraph = application.getObjectGraph().plus(getActivityModules());

        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        mActivityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage
        // collected as soon as possible.
        mActivityGraph = null;
        super.onDestroy();
    }

    /**
     * Inject the supplied {@code object} using the activity {@link ObjectGraph}.
     */
    @Override
    public <T> T inject(T object) {
        return mActivityGraph.inject(object);
    }

    public ObjectGraph getObjectGraph() {
        return mActivityGraph;
    }

    protected abstract Object[] getActivityModules();
}