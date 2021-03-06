package ie.markomeara.irelandtraintimes.stationlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ie.markomeara.irelandtraintimes.R;
import ie.markomeara.irelandtraintimes.data.IrishRailService;
import ie.markomeara.irelandtraintimes.di.Injector;
import ie.markomeara.irelandtraintimes.model.Station;

/**
 * Created by markomeara on 19/04/2017.
 */

public class StationListFragment extends Fragment implements StationListContract.View {

    @Inject
    IrishRailService irishRailService;

    @BindView(R.id.station_list)
    RecyclerView stationList;

    private StationListAdapter stationListAdapter;
    private StationListContract.Presenter presenter;

    public static StationListFragment newInstance() {
        return new StationListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stationlist_fragment, container, false);
        ButterKnife.bind(this, view);
        Injector.get().inject(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new StationListPresenter(this, irishRailService);

        stationList.setLayoutManager(new LinearLayoutManager(getContext()));
        stationListAdapter = new StationListAdapter();
        stationList.setAdapter(stationListAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void displayStations(List<Station> stations) {
        stationListAdapter.updateStationsList(stations);
    }
}
