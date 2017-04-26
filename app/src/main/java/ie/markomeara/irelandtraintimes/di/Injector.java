package ie.markomeara.irelandtraintimes.di;

/**
 * Created by markomeara on 19/04/2017.
 */

public class Injector {

    private static AppComponent sComponent = null;

    public static void init() {
        if (sComponent == null) {
            sComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
        }
    }

    public static AppComponent get() {
        return sComponent;
    }
}
