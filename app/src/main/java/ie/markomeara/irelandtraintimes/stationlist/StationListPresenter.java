package ie.markomeara.irelandtraintimes.stationlist;

import ie.markomeara.irelandtraintimes.data.IrishRailService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static ie.markomeara.irelandtraintimes.util.ValidationUtil.checkNotNull;

/**
 * Created by markomeara on 19/04/2017.
 */

public class StationListPresenter implements StationListContract.Presenter {

    private StationListContract.View view;
    private IrishRailService irishRailService;

    public StationListPresenter(StationListContract.View view, IrishRailService irishRailService) {
        this.view = checkNotNull(view, "View cannot be null");
        this.irishRailService = checkNotNull(irishRailService, "irishRailService cannot be null");
    }

    @Override
    public void start() {
        irishRailService.getAllStations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        stationList -> view.displayStations(stationList)
                );
    }
}
