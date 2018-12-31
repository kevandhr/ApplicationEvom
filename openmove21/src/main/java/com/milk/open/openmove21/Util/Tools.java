package com.milk.open.openmove21.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unused")
public class Tools {

	/**
	 * check network available or not
	 * 
	 * @param ct
	 */
//	public static void checkNetwork(final Context ct) {
//		if (!isNetworkAvailable(ct)) {
//			TextView msg = new TextView(ct);
//			msg.setText("no network");
//			new AlertDialog.Builder(ct)
//					.setIcon(R.drawable.jinzhitu)
//					.setTitle("network msg")
//					.setView(msg)
//					.setPositiveButton("OK",
//							new DialogInterface.OnClickListener() {
//
//								@Override
//								public void onClick(DialogInterface dialog,
//										int which) {
//									ct.startActivity(new Intent(
//											Settings.ACTION_WIRELESS_SETTINGS));
//								}
//							}).create().show();
//		}
//	}

	/**
	 * network state
	 */
	public static boolean isNetworkAvailable(Context ct) {
		ConnectivityManager conma = (ConnectivityManager) ct
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conma == null) {
			// Toast.makeText(ct, "no network1", Toast.LENGTH_SHORT).show();
			return false;
		} else {
			NetworkInfo[] info = conma.getAllNetworkInfo();
			if (info != null) {
				for (NetworkInfo network : info) {
					if (network.getState() == NetworkInfo.State.CONNECTED) {
						// Toast.makeText(ct, "network accessed",
						// Toast.LENGTH_SHORT).show();
						return true;
					}
				}
			}
		}
		// Toast.makeText(ct, "no network",
		// Toast.LENGTH_SHORT).show();
		return false;
	}

//	public static void checknotLogin(final Context ct) {
//		if (SPLogin.islogin == 0) {
//			TextView msg = new TextView(ct);
//			msg.setText(R.string.kankebiao_notlogin);
//			new AlertDialog.Builder(ct)
//					.setIcon(R.drawable.jinzhitu)
//					.setTitle("login tip")
//					.setView(msg)
//					.setPositiveButton(R.string.kankebiao_mashangdenglu,
//							new DialogInterface.OnClickListener() {
//
//								@Override
//								public void onClick(DialogInterface dialog,
//										int which) {
//									ct.startActivity(new Intent(ct
//											.getApplicationContext(),
//											ActivityLogin.class));
//								}
//							}).create().show();
//		}
//	}
}