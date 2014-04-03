package hilt;

import dagger.ObjectGraph;

/**
 * An instance which is capable of injecting dependencies.
 */
public interface Injector {
    /**
     * Inject <code>object</code>
     */
    <T> T inject(T object);

    ObjectGraph getObjectGraph();
}