package ie.markomeara.irelandtraintimes.stationlist;

import ie.markomeara.irelandtraintimes.data.IrishRailApi;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static ie.markomeara.irelandtraintimes.util.ValidationUtil.checkNotNull;

/**
 * Created by markomeara on 19/04/2017.
 */

public class StationListPresenter implements StationListContract.Presenter {

    private StationListContract.View view;
    private IrishRailApi irishRailApi;

    public StationListPresenter(StationListContract.View view, IrishRailApi irishRailApi) {
        this.view = checkNotNull(view, "View cannot be null");
        this.irishRailApi = checkNotNull(irishRailApi, "Irish rail API cannot be null");
    }

    @Override
    public void start() {
        irishRailApi.getAllStations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        stationList -> view.displayStations(stationList.getStations())
                );
    }
}
