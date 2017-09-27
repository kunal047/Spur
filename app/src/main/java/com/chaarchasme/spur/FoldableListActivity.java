package com.chaarchasme.spur;

/**
 * Created by kunal on 24-09-2017.
 */

import android.os.Bundle;

import com.alexvasilkov.foldablelayout.FoldableListLayout;
import com.chaarchasme.spur.model.PaintingsAdapter;

import static com.chaarchasme.spur.MainActivity.paintingList;


public class FoldableListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foldable_list);

        FoldableListLayout foldableListLayout = (FoldableListLayout) findViewById(R.id.foldable_list);
        foldableListLayout.setAdapter(new PaintingsAdapter(FoldableListActivity.this, paintingList));

    }

}
