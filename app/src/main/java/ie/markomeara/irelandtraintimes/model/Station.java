package ie.markomeara.irelandtraintimes.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by markomeara on 19/04/2017.
 */

@Root(name = "objStation")
public class Station {

    @Element(name = "StationDesc")
    private String stationDescription;

    @Element(name = "StationAlias", required = false)
    private String stationAlias;

    @Element(name = "StationLatitude")
    private Double stationLatitude;

    @Element(name = "StationLongitude")
    private Double stationLongitude;

    @Element(name = "StationCode")
    private String stationCode;

    @Element(name = "StationId")
    private int stationId;

    public String getStationDescription() {
        return stationDescription;
    }

    public String getStationAlias() {
        return stationAlias;
    }

    public Double getStationLatitude() {
        return stationLatitude;
    }

    public Double getStationLongitude() {
        return stationLongitude;
    }

    public String getStationCode() {
        return stationCode;
    }

    public int getStationId() {
        return stationId;
    }
}
