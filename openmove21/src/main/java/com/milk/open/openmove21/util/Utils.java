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

    public static int n_data = 10;
    public static String[] ticketid = {"123", "456", "789", "246", "357", "123", "456", "789", "246", "357"};
    public static String[] states = {"To be purchased", "To be purchased", "To be purchased", "To be purchased", "To be purchased",
            "To be purchased", "To be purchased", "To be purchased", "To be purchased", "To be purchased"};
    public static String[] scopes = {"TRENTO URBAN", "TRENTO URBAN", "PERGINE URBAN", "PERGINE URBAN", "PERGINE URBAN",
            "TRENTO URBAN", "TRENTO URBAN", "PERGINE URBAN", "PERGINE URBAN", "PERGINE URBAN"};
    public static String[] timelimits = {"70 minutes", "70 minutes", "150 minutes", "6 h", "1 Day",
            "70 minutes", "70 minutes", "150 minutes", "6 h", "1 Day"};
    public static double[] money = {1.10, 1.60, 1.60, 2.00, 2.00, 1.60, 2.00, 2.00,1.60, 2.00};

    public static int n_mytickets = 4;

    public static String[] menuitems = {
            "Search tickets", "Search trips", "Timetable", "Advise",
            "My tickets", "Profile", "Credit", "Privilege cards",
            "Gift", "Statistics", "About"};
    public static int[] menuitemimgs = {
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white,
            R.drawable.ticket_gouwuche_white, };

    public static int n_userinfo = 6;
    public static String[] userinfostr1 = {"Name", "Date of birth", "Email", "Name", "Date of birth", "Email"};
    public static String[] userinfostr2 = {"yu", "14/03/1995", "shenyu123@gmail.com", "yu", "14/03/1995", "shenyu123@gmail.com"};

    public static int n_ticketrecords = 10;
    public static String[] ticketrecordstr1 = {
            "31/12/18 - 19:32:18",
            "29/12/18 - 17:17:04",
            "25/12/18 - 13:59:42",
            "25/12/18 - 11:36:28",
            "29/12/18 - 17:17:04",
            "25/12/18 - 13:59:42",
            "25/12/18 - 11:36:28",
            "31/12/18 - 19:32:18",
            "29/12/18 - 17:17:04",
            "23/12/18 - 17:23:18"};
    public static String[] ticketrecordstr2 = {
            "TT1416",
            "66 - S - S.Bartolameo FS",
            "TT1472",
            "TT1668",
            "TT2512",
            "TT1416",
            "66 - S - S.Bartolameo FS",
            "TT1472",
            "TT1668",
            "TT2512"};
}
