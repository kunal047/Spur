package com.chaarchasme.spur;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.chaarchasme.spur.model.Painting;
import com.github.florent37.materialleanback.MaterialLeanBack;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static List<String> paintingList = new ArrayList<>();

    MaterialLeanBack materialLeanBack;
    Toolbar toolbar;
    DrawerLayout mDrawer;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.setDrawerListener(mDrawerToggle);

        materialLeanBack = (MaterialLeanBack) findViewById(R.id.materialLeanBack);

        materialLeanBack.setCustomizer(new MaterialLeanBack.Customizer() {
            @Override
            public void customizeTitle(TextView textView) {
                textView.setTypeface(null, Typeface.BOLD);
            }
        });
        new PrepareVideos().execute();

        //Picasso.with(getApplicationContext())
        //        .load("http://www.journaldugeek.com/wp-content/blogs.dir/1/files/2015/01/game-of-thrones-saison-5-documentaire.jpg")
        //        .fit().centerCrop()
        //        .into(materialLeanBack.getImageBackground());

        materialLeanBack.setAdapter(new MaterialLeanBack.Adapter<TestViewHolder>() {
            @Override
            public int getLineCount() {
                return 4;
            }

            @Override
            public int getCellsCount(int line) {
                return 10;
            }

            @Override
            public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int line) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_test, viewGroup, false);
                return new TestViewHolder(view);
            }

            @Override
            public void onBindViewHolder(TestViewHolder viewHolder, int i) {
                viewHolder.textView.setText("test " + i);

                String url = "http://www.lorempixel.com/40" + viewHolder.row + "/40" + viewHolder.cell + "/";
                paintingList.add(url);
                Picasso.with(viewHolder.imageView.getContext()).load(url).into(viewHolder.imageView);
            }

            @Override
            public String getTitleForRow(int row) {
                switch (row) {
                    case 0:
                        return "Daily Feed";
                    case 1:
                        return "Quotes";
                    case 2:
                        return "Videos";
                    case 3:
                        return "Stories";
                    default:
                        return "null";
                }
            }


//            @Override
//            public boolean hasRowTitle(int row) {
//                return row != 6;
//            }


            //region customView

//            @Override
//            public RecyclerView.ViewHolder getCustomViewForRow(ViewGroup viewgroup, int row) {
//                if (row == 3) {
//                    View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.header, viewgroup, false);
//                    return new RecyclerView.ViewHolder(view) {
//                    };
//                } else
//                    return null;
//            }

//            @Override
//            public boolean isCustomView(int row) {
//                return row == 3;
//            }

            @Override
            public void onBindCustomView(RecyclerView.ViewHolder viewHolder, int row) {
                super.onBindCustomView(viewHolder, row);
            }

            //endregion

        });

        materialLeanBack.setOnItemClickListener(new MaterialLeanBack.OnItemClickListener() {
            @Override
            public void onTitleClicked(int row, String text) {
                Toast.makeText(getApplicationContext(), "onTitleClicked " + row + " " + text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClicked(int row, int column) {

                Intent intent = new Intent(MainActivity.this, FoldableListActivity.class);
                startActivity(intent);


                Toast.makeText(getApplicationContext(), "onItemClicked " + row + " " + column, Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialLeanBack.smoothScrollTo(5, 6);
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }

    public class PrepareVideos extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d(TAG, "doInBackground: sending request ------------------------ ");

            AndroidNetworking.get("http://192.168.1.108:8000/spur_data/videos_data/?category=famous person")
//                    ?category=famous%20person
                    .setPriority(Priority.HIGH)
                    .build()


//                    .getAsJSONObject(new JSONObjectRequestListener() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                Log.d(TAG, "onResponse: repsonse is --------------------------- " + response.get("foo"));
//                                JSONObject arr = (JSONObject) response.get("foo");
//                                Log.d(TAG, "onResponse: arr is ------------ " + arr.toString());
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onError(ANError anError) {
//
//                        }
//                    });
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Log.d(TAG, "onResponse: response is ------------------------------- " + response.get(0));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d(TAG, "onError: error is ----------------------- " + anError.toString());

                        }
                    });
//                    .getAsString(new StringRequestListener() {
//                        @Override
//                        public void onResponse(String response) {
//                            Log.d(TAG, "onResponse: response is ------------------------------- " + response);
//                        }
//
//                        @Override
//                        public void onError(ANError anError) {
//                            Log.d(TAG, "onError: error is --------------------------------- " + anError.toString());
//                        }
//                    });

            return null;
        }
    }
}
