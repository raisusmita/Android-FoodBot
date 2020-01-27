package com.example.android_foodbot_admin.Common;

import android.net.Uri;

import com.example.android_foodbot_admin.Model.Request;
import com.example.android_foodbot_admin.User;

public class Common {
    public  static User currentUser;
    public static int Total;
    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final int PICK_IMAGE_REQUEST = 71;

    public  static String convertCodeToStatus(String code)
    {
        switch (code) {
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

}
