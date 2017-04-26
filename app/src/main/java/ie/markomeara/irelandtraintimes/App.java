package ie.markomeara.irelandtraintimes;

import android.app.Application;

import ie.markomeara.irelandtraintimes.di.Injector;

/**
 * Created by markomeara on 19/04/2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.init();
    }
}
