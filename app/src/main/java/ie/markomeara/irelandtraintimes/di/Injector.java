package ie.markomeara.irelandtraintimes.di;

/**
 * Created by markomeara on 19/04/2017.
 */

public class Injector {

    private static AppComponent component = null;

    public static void init() {
        if (component == null) {
            component = DaggerAppComponent.builder().appModule(new AppModule()).build();
        }
    }

    public static AppComponent get() {
        return component;
    }

    public static void setComponent(AppComponent component) {
        Injector.component = component;
    }
}
