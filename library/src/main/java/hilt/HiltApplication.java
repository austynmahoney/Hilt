package hilt;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Base Application that creates an {@link ObjectGraph} and adds all modules
 * provided by {@link #getAppModules}
 */
public abstract class HiltApplication extends Application implements Injector {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        HiltAppModule appModule = new HiltAppModule(this.getApplicationContext());

        List<Object> modules = new ArrayList<>();
        modules.add(appModule);
        modules.addAll(getAppModules());

        mObjectGraph = ObjectGraph.create(modules.toArray());

        mObjectGraph.inject(this);
    }

    /**
     * Helper for casting a {@link android.content.Context} to {@link hilt.HiltApplication}
     *
     * @param context The context used to retrieve {@link HiltApplication}
     */
    public static HiltApplication get(Context context) {
        try {
            return ((HiltApplication) context.getApplicationContext());
        } catch (ClassCastException e) {
            throw new ClassCastException("Application must extend from HiltApplication");
        }
    }

    protected abstract List<Object> getAppModules();

    /**
     * Inject the supplied {@code object} using the application {@link ObjectGraph}.
     */
    @Override
    public <T> T inject(T object) {
        return mObjectGraph.inject(object);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }
}