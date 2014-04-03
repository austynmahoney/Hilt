package hilt.support;

import android.os.Bundle;

import dagger.ObjectGraph;
import hilt.Injector;

/**
 * Base Fragment which performs injection using the activity-scoped object graph
 */
public abstract class HiltSupportFragment extends android.support.v4.app.Fragment implements Injector {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject(this);
    }

    /**
     * Inject the supplied {@code object} using the {@link dagger.ObjectGraph} of the
     * {@link hilt.HiltActivity} this Fragment is contained within.
     */
    @Override
    public <T> T inject(T object) {
        return getObjectGraph().inject(object);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        // Assume that it lives within a HiltSupportActivity host
        return ((HiltActionBarActivity) getActivity()).getObjectGraph();
    }
}