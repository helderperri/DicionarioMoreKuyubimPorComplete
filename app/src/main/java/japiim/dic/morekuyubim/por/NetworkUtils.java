package japiim.dic.morekuyubim.por;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NetworkUtils {

    private static int TYPE_WIFI = 1;
    private static int TYPE_MOBILE = 2;
    private static int TYPE_NOT_CONNECTED = 0;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Network network = connectivityManager.getActiveNetwork();
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                    && network != null) {
                Log.d("GNM-JPP", "TYPE_WIFI: ");
                return TYPE_WIFI;

            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE
                    && network != null) {
                Log.d("GNM-JPP", "TYPE_MOBILE: ");
                return TYPE_MOBILE;
            }
        }
        Log.d("GNM-JPP WIFI", "TYPE_NOT_CONNECTED");
        return TYPE_NOT_CONNECTED;
    }

    public static boolean isNetworkConnected(Context context) {
        int networkStatus = getConnectivityStatus(context);
        if (networkStatus == TYPE_WIFI || networkStatus == TYPE_MOBILE) {
            return true;
        } else {
            return false;
        }
    }
}
