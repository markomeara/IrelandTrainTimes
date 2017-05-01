package ie.markomeara.irelandtraintimes.stationlist;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import ie.markomeara.irelandtraintimes.RxSchedulersTestRule;
import ie.markomeara.irelandtraintimes.data.IrishRailService;
import ie.markomeara.irelandtraintimes.model.Station;
import io.reactivex.Observable;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by markomeara on 28/04/2017.
 */

public class StationListPresenterTest {

    @Rule
    public RxSchedulersTestRule rxSchedulersTestRule = new RxSchedulersTestRule();

    @Mock
    StationListContract.View mockStationListView;

    @Mock
    IrishRailService mockIrishRailService;

    private StationListContract.Presenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new StationListPresenter(mockStationListView, mockIrishRailService);
    }

    @Test
    public void testStationsRetrievedAndDisplayedOnStart() {
        List<Station> returnedStationList = new ArrayList<>();
        Observable<List<Station>> stationListObservable = Observable.just(returnedStationList);

        when(mockIrishRailService.getAllStations()).thenReturn(stationListObservable);

        presenter.start();

        verify(mockStationListView).displayStations(eq(returnedStationList));

    }

}