package ie.markomeara.irelandtraintimes.stationlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ie.markomeara.irelandtraintimes.R;

public class StationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stationlist_activity);
        initFragment(StationListFragment.newInstance());
    }

    private void initFragment(Fragment stationListFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, stationListFragment);
        transaction.commit();
    }
}
