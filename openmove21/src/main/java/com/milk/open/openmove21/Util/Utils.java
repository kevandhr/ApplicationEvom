package com.milk.open.openmove21.Util;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;

public class Utils {

    public static boolean IS_TEST_DATA = true;

    public static Point getScreenSize(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        int screen_width_px = displayMetrics.widthPixels;
        int screen_height_px = displayMetrics.heightPixels;
        Point point = new Point(screen_width_px, screen_height_px);
        return point;
    }

    public static int n_data = 10;
    public static String[] ticketid = {"123", "456", "789", "246", "357", "123", "456", "789", "246", "357"};
    public static String[] states = {"To be purchased", "To be purchased", "To be purchased", "To be purchased", "To be purchased",
            "To be purchased", "To be purchased", "To be purchased", "To be purchased", "To be purchased"};
    public static String[] scopes = {"TRENTO URBAN", "TRENTO URBAN", "PERGINE URBAN", "PERGINE URBAN", "PERGINE URBAN",
            "TRENTO URBAN", "TRENTO URBAN", "PERGINE URBAN", "PERGINE URBAN", "PERGINE URBAN"};
    public static String[] timelimits = {"70 minutes", "70 minutes", "150 minutes", "6 h", "1 Day",
            "70 minutes", "70 minutes", "150 minutes", "6 h", "1 Day"};
    public static double[] money = {0.90, 1.60, 1.60, 2.00, 2.00, 1.60, 2.00, 2.00,1.60, 2.00};

    public static String[] menuitems = {
            "Search tickets", "Search trips", "Timetable", "Advise",
            "My tickets", "Profile", "Credit", "Privilege cards",
            "Gift", "Statistics", "About"};

}
