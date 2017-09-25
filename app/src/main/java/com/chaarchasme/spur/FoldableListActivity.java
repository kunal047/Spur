package com.chaarchasme.spur;

/**
 * Created by kunal on 24-09-2017.
 */

import android.os.Bundle;
import android.util.Log;

import com.alexvasilkov.foldablelayout.FoldableListLayout;
import com.chaarchasme.spur.model.PaintingsAdapter;

import static com.chaarchasme.spur.MainActivity.paintingList;


public class FoldableListActivity extends BaseActivity {


    private static final String TAG = "FoldableListActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foldable_list);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        Log.d(TAG, "onCreate: painting list contains -------------- " + paintingList);


        FoldableListLayout foldableListLayout = (FoldableListLayout) findViewById(R.id.foldable_list);
        foldableListLayout.setAdapter(new PaintingsAdapter(getApplicationContext(), paintingList));

    }

}
