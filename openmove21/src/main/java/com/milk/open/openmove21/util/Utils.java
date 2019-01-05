package com.milk.open.openmove21.util;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import com.milk.open.openmove21.R;

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

    public static int TC_CIVEZZANO = 5678;

    public static int n_data = 10;
    public static String[] ticketid = {"123", "456", "789", "246", "357", "123", "456", "789", "246", "357",
            "1111"};
    public static String[] states = {"To be purchased", "To be purchased", "To be purchased", "To be purchased", "To be purchased",
            "To be purchased", "To be purchased", "To be purchased", "To be purchased", "To be purchased", "Valid"};
    public static String[] scopes = {"TRENTO URBAN", "TRENTO", "PERGINE URBAN", "PERGINE URBAN", "PERGINE URBAN",
            "TRENTO URBAN", "TRENTO URBAN", "PERGINE URBAN", "PERGINE URBAN", "PERGINE URBAN",
            "CIVEZZANO/MOCHENA/SILLE"};
    public static String[] timelimits = {"70 minutes", "6 hours", "150 minutes", "6 h", "1 Day",
            "70 minutes", "70 minutes", "150 minutes", "6 hours", "1 Day",
            "(PASSING THROUGH VALSUGANA)"};
    public static double[] money = {1.10, 1.60, 1.60, 2.00, 2.00, 1.60, 2.00, 2.00,1.60, 2.00, 1.60};

    public static String myticket_arr = "TRENTO";
    public static int n_mytickets = 3;

    public static String[] menuitems = {
            "Search tickets", "Search trips", "Timetable", "Advise",
            "My tickets", "Profile", "Credit", "Privilege cards",
            "Gift", "Statistics", "About"};
    public static int[] menuitemimgs = {
            R.drawable.fmenu_ic_gongneng01,
            R.drawable.fmenu_ic_gongneng02,
            R.drawable.fmenu_ic_gongneng03,
            R.drawable.fmenu_ic_gongneng04,
            R.drawable.fmenu_ic_gongneng05,
            R.drawable.fmenu_ic_gongneng06,
            R.drawable.fmenu_ic_gongneng07,
            R.drawable.fmenu_ic_gongneng08,
            R.drawable.fmenu_ic_gongneng09,
            R.drawable.fmenu_ic_gongneng10,
            R.drawable.fmenu_ic_gongneng11, };

    public static int n_userinfo = 9;
    public static String[] userinfostr1 = {
            "Purechase date",
            "Origin",
            "Destination",
            "Price",
            "Routing",
            "Validity",
            "Expiry",
            "Travellers",
            "ID"};
    public static String[] userinfostr2 = {
            "02/01/19 - 10:06",
            "Civezzano/Mochena/Sille",
            "Trento",
            "1.60 €",
            "VALSUGANA",
            "6 Hours",
            "31/12/19 - 23:59:59",
            "1",
            "TT296197",};

    public static int n_ticketrecords = 3;
    public static String[] ticketrecordstr1 = {
            "25/12/18 - 13:59:42",
            "25/12/18 - 11:36:28",
            "29/12/18 - 17:17:04",
            "25/12/18 - 13:59:42",
            "25/12/18 - 11:36:28",
            "29/12/18 - 17:17:04",
            "31/12/18 - 19:32:18"};
    public static String[] ticketrecordstr2 = {
            "TT1416",
            "TT1472",
            "TT1668",
            "TT2512",
            "66 - S - S.Bartolameo FS",
            "TT1416",
            "TT1472"};
}
