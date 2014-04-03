package hilt;

import android.app.Fragment;
import android.os.Bundle;

import dagger.ObjectGraph;

/**
 * Base Fragment which performs injection using the activity-scoped object graph
 */
public abstract class HiltFragment extends Fragment implements Injector {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject(this);
    }

    /**
     * Inject the supplied {@code object} using the {@link ObjectGraph} of the
     * {@link HiltActivity} this Fragment is contained within.
     */
    @Override
    public <T> T inject(T object) {
        return getObjectGraph().inject(object);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        // Assume that it lives within a HiltActivity
        return ((HiltActivity) getActivity()).getObjectGraph();
    }
}