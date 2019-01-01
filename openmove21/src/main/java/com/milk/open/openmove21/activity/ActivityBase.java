package com.milk.open.openmove21.activity;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.milk.open.openmove21.Util.Utils;

/**
 * 应用中Activity的基类，对原有的Activity类进行扩展
 */
public abstract class ActivityBase extends AppCompatActivity {

	/**
	 * 当前activity所持有的所有网络请求
	 */
	// protected List<AsyncBaseRequest> mAsyncRequests;

	/**
	 * 当前activity所持有的Handler
	 */
	protected Handler mHandler;

	/**
	 * 当前activity所持有的线程池对象
	 */
	// protected DefaultThreadPool mDefaultThreadPool;

	protected Context mContext;

	private Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        if (getSupportActionBar()!= null){
            getSupportActionBar().hide();
        }

		getScreenSize();

		int layoutId = getLayoutId();
		if (layoutId != 0) {
			setContentView(getLayoutId());
		}

		mContext = this.getApplicationContext();
		// ((EveryoneApplication) this.getApplication()).addActivity(this);

//		mHandler = new Handler();
		// mAsyncRequests = new ArrayList<AsyncBaseRequest>();
		// mDefaultThreadPool = DefaultThreadPool.getInstance();

		// 初始化组件
		setupView();
		// 初始化数据
		initialized();
	}

	/**
	 * 取消当前Activity相关的网络请求
	 */
	/*
	 * private void cancelAllRequest() { if (mAsyncRequests != null &&
	 * mAsyncRequests.size() > 0) { try { int size = mAsyncRequests.size(); for
	 * (int i = 0; i < size; i++) { AsyncBaseRequest request =
	 * mAsyncRequests.get(i); Thread thread = new Thread(request); if
	 * (thread.isAlive() || !Thread.interrupted()) {
	 * request.setInterrupted(true); }
	 *
	 * HttpURLConnection conn = request.getRequestConn(); if (conn != null) { //
	 * conn.disconnect(); System.err.println("onDestroy disconnect URL: " +
	 * conn.getURL()); }
	 *
	 * mAsyncRequests.remove(request); size = mAsyncRequests.size(); } } catch
	 * (Exception e) { // Auto-generated catch block e.printStackTrace(); }
	 * } }
	 */


	/**
	 * get screen size
	 */
	public void getScreenSize() {
		Point point = Utils.getScreenSize(this);
		UtilDataConstants.sHeight = point.x;
		UtilDataConstants.sWidth = point.y;
	}

	/**
	 * 显示Toast形式的提示信息
	 */
	public void toast(String msg) {
		if (null == msg) {
			return;
		}
		if (null == toast)
			toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
		else
			toast.setText(msg);
		toast.show();
	}

	public void toast(int id) {
		if (null == toast)
			toast = Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT);
		else
			toast.setText(id);
		toast.show();
	}


	// public List<AsyncBaseRequest> getAsyncRequests() {
	// return mAsyncRequests;
	// }

	public Handler getHandler() {
		return mHandler;
	}

	// public DefaultThreadPool getDefaultThreadPool() {
	// return mDefaultThreadPool;
	// }

	public Context getContext() {
		return mContext;
	}

	@Override
	protected void onPause() {
		/**
		 * 在activity暂停的时候，同时设置终止标识，终止异步线程
		 */
		// cancelAllRequest();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		/**
		 * 在activity销毁的时候，同时设置终止标识，终止异步线程
		 */
		// cancelAllRequest();
		super.onDestroy();
	}

    /**
     * 初始化组件
     */
    protected abstract void setupView();

    /**
     * 初始化数据
     */
    protected abstract void initialized();

    /**
     * 布局文件ID
     *
     * @return
     */
    protected abstract int getLayoutId();



}
