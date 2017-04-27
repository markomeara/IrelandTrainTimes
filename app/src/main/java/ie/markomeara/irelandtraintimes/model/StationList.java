package ie.markomeara.irelandtraintimes.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by markomeara on 19/04/2017.
 */

@Root(name = "ArrayOfObjStationData")
public class StationList {

    @ElementList(inline = true)
    private List<Station> stations;

    public List<Station> getStations() {
        return stations;
    }
}
