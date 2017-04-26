package ie.markomeara.irelandtraintimes.stationlist;

import java.util.List;

import ie.markomeara.irelandtraintimes.model.Station;

/**
 * Created by markomeara on 19/04/2017.
 */

public interface StationListContract {

    interface View {
        void displayStations(List<Station> stations);
    }

    interface Presenter {

        void start();
    }
}
