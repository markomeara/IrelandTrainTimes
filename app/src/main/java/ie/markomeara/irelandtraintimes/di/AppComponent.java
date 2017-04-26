package ie.markomeara.irelandtraintimes.di;

import javax.inject.Singleton;

import dagger.Component;
import ie.markomeara.irelandtraintimes.stationlist.StationListFragment;

/**
 * Created by markomeara on 19/04/2017.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(StationListFragment fragment);
}
