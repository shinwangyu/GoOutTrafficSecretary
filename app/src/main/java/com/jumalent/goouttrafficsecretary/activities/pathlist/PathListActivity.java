package com.jumalent.goouttrafficsecretary.activities.pathlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jumalent.goouttrafficsecretary.R;
import com.jumalent.goouttrafficsecretary.activities.BaseActivity;

public class PathListActivity extends BaseActivity implements PathListView{
    private final static String TAG = PathListActivity.class.getSimpleName();

    private PathListPresenter myPresenter = null;



    //Bind setting( Butter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_list);

        // set layout
        initLayout();



        myPresenter = new PathListPresenterImpl();
    }



    protected void initLayout() {

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // FloatingAction Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        //        //recycler view
//        mRecyclerView.setHasFixedSize(true);
//
//        //use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        //specify an adapter (see also next example)
//        mAdapter = new MultiItemAdapter() {
//            @Override
//            public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return new MyViewHolder(parent.getRootView());
//            }
//        };
//
//
//        mRecyclerView.setAdapter(mAdapter);



    }


}
