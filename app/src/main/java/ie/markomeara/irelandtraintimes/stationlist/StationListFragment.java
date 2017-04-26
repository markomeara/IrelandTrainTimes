package ie.markomeara.irelandtraintimes.stationlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ie.markomeara.irelandtraintimes.R;
import ie.markomeara.irelandtraintimes.data.IrishRailApi;
import ie.markomeara.irelandtraintimes.di.Injector;
import ie.markomeara.irelandtraintimes.model.Station;

/**
 * Created by markomeara on 19/04/2017.
 */

public class StationListFragment extends Fragment implements StationListContract.View {

    @Inject
    IrishRailApi irishRailApi;

    private StationListContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stationlist_fragment, container);
        ButterKnife.bind(this, view);
        Injector.get().inject(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new StationListPresenter(this, irishRailApi);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void displayStations(List<Station> stations) {
        // TODO
    }
}
