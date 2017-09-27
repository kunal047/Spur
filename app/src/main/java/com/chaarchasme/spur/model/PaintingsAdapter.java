package com.chaarchasme.spur.model;

/**
 * Created by kunal on 24-09-2017.
 */


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaarchasme.spur.R;

import java.util.List;

import static com.chaarchasme.spur.MainActivity.paintingList;


public class PaintingsAdapter extends BaseAdapter {

    private static final String TAG = "PaintingsAdapter";
    private final Context context;
    private List<String> paintingsList;
    private View mView;
    private LayoutInflater inflater;

    public PaintingsAdapter(Context context, List<String> paintingsList) {
        this.paintingsList = paintingsList;
        this.context = context;
        inflater = (LayoutInflater.from(context));
        Log.d(TAG, "onCreate: painting list contains -------------- " + paintingList);

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        mView = inflater.inflate(R.layout.activity_foldable_list, null);
        TextView textViewImageName = mView.findViewById(R.id.list_item_title);
        ImageView imageViewQuote = mView.findViewById(R.id.list_item_image);
        textViewImageName.setText("Test " + i);
        Glide.with(context)
                .load(paintingsList.get(i))
                .into(imageViewQuote);


        return null;
    }


    @Override
    public int getCount() {
        return paintingList.size();
//        return paintingsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}



