package com.example.android_foodbot.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.net.ConnectivityManagerCompat;

import com.example.android_foodbot.AccountActivity.User;

public class Common {
    public static User currentUser;
    public static String convertCodeToStatus(String status){
        switch (status) {
            case "0":
                return "Placed";
            case "1":
                return "Processing";
            case "2":
                return "Ready to pick";
            default:
                return "Served";
        }

    }

    public  static boolean isConnectedToInternet(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager !=null)
        {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info!=null)
            {
                for (int i = 0; i<info.length; i++)
                {
                    if (info[i].getState()==NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return  false;
    }

    public static  final String DELETE = "Delete";
//    public static  final String USER_KEY = "User";
//    public static  final String PWD_KEY = "Password";


}
