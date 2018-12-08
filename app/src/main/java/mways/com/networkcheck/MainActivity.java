package mways.com.networkcheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.scottyab.rootbeer.RootBeer;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    ConnectivityReceiver connectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectivityReceiver = new ConnectivityReceiver(this);
        checkRoot();
    }

    private void checkRoot() {

        RootBeer rootBeer = new RootBeer(getApplicationContext());
        if (rootBeer.isRooted()) {
            Toast.makeText(MainActivity.this, "Rooted Device", Toast.LENGTH_SHORT).show();
            //we found indication of root
        } else {
            Toast.makeText(MainActivity.this, "Not Rooted Device", Toast.LENGTH_SHORT).show();
            //we didn't find indication of root
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        connectivityReceiver.setConnectivityReceiverListener(this);
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Toast.makeText(this, "Network Connection is >>>>>" + isConnected, Toast.LENGTH_SHORT).show();

    }
}
