package com.milk.open.openmove21.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.milk.open.openmove21.R;
import com.milk.open.openmove21.util.Utils;
import com.milk.open.openmove21.adapter.AdapterMenuList;
import com.milk.open.openmove21.model.ModelMenuItem;

import java.util.ArrayList;

public class FragmentMenu extends FragmentBase implements AbsListView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView iv_close;
    private ListView mListView;
    private ArrayList<ModelMenuItem> arraydata;
    private AdapterMenuList mAdapter;

    private OnFragmentInteractionListener mListenerActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        arraydata = new ArrayList<ModelMenuItem>();
        for(int i=0;i<11;i++){
            arraydata.add(new ModelMenuItem(""+i, Utils.menuitems[i], Utils.menuitemimgs[i]));
        }

        mAdapter = new AdapterMenuList(getActivity(), arraydata);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        mListView = (ListView) view.findViewById(R.id.lv_fmenu);
        mListView.setAdapter(mAdapter);
        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        iv_close = view.findViewById(R.id.iv_close_fmenu);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenerActivity.onFragmentInteraction("iv_close onclick");
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        toast("onItemClick="+position);
        mListenerActivity.onFragmentInteraction(position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListenerActivity = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListenerActivity = null;
    }

    public FragmentMenu() {
    }
    private static FragmentMenu fragment;
    public static FragmentMenu getInstance(){
        if (null == fragment){
            fragment = new FragmentMenu();
        }
        return fragment;
    }

    public static FragmentMenu newInstance(String param1, String param2) {
        FragmentMenu fragment = FragmentMenu.getInstance();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int i);
        public void onFragmentInteraction(String msg);
    }

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener mListenerActivity){
        this.mListenerActivity = mListenerActivity;
    }
}
