package ie.markomeara.irelandtraintimes.data;

import ie.markomeara.irelandtraintimes.model.StationList;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by markomeara on 19/04/2017.
 */

public interface IrishRailApi {

    @GET("getAllStationsXML")
    Observable<StationList> getAllStations();


}
