package ie.markomeara.irelandtraintimes.data;

import java.util.List;

import ie.markomeara.irelandtraintimes.model.Station;
import ie.markomeara.irelandtraintimes.model.StationList;
import io.reactivex.Observable;

/**
 * Created by markomeara on 26/04/2017.
 */

public class IrishRailService {

    private IrishRailApi irishRailApi;

    public IrishRailService(IrishRailApi irishRailApi) {
        this.irishRailApi = irishRailApi;
    }

    public Observable<List<Station>> getAllStations() {
        return Observable.defer(() -> irishRailApi.getAllStations())
                // TODO Deduplication
                .map(StationList::getStations);
    }
}
