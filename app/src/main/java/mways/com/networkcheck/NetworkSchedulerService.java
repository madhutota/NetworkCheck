package mways.com.networkcheck;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkSchedulerService extends JobService implements
        ConnectivityReceiver.ConnectivityReceiverListener {

    private static final String TAG = NetworkSchedulerService.class.getSimpleName();

    public ConnectivityReceiver mConnectivityReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        mConnectivityReceiver = new ConnectivityReceiver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * When the app's MainActivity is created, it starts this service. This is so that the
     * activity and this service can communicate back and forth. See "setUiCallback()"
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }


    @Override
    public boolean onStartJob(JobParameters params) {
        registerReceiver(mConnectivityReceiver, new IntentFilter(Constants.CONNECTIVITY_ACTION));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        unregisterReceiver(mConnectivityReceiver);
        return true;
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Toast.makeText(this, "From Job Sheduler >>>>>>>"+isConnected, Toast.LENGTH_SHORT).show();

    }
}