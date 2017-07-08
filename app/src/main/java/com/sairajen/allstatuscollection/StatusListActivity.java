package com.sairajen.allstatuscollection;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.sairajen.allstatuscollection.adapter.StatusAdapter;
import com.sairajen.allstatuscollection.model.Status;
import com.sairajen.allstatuscollection.utils.Helper;
import com.sairajen.allstatuscollection.utils.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StatusListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private List<Status> statusList;
    private StatusAdapter statusAdapter;

    private AdView adView;
    private InterstitialAd interstitialAd;

    private String tableName = "";
    private final static String TABLE_NAME = "table_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);

        if (getIntent().hasExtra(TABLE_NAME))
            tableName = getIntent().getExtras().getString(TABLE_NAME);

        initToolbar();

        MobileAds.initialize(getApplicationContext(),getResources().getString(R.string.banner));
        adView = (AdView) findViewById(R.id.adStatusList);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        prepareInterstitialAds();

        progressBar = (ProgressBar) findViewById(R.id.progressBarStatus);
        recyclerView = (RecyclerView) findViewById(R.id.statusListRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StatusListActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        statusList = new ArrayList<>();

        progressBar.setVisibility(View.VISIBLE);
        int time = (int) (System.currentTimeMillis());
        StringRequest request = new StringRequest(Request.Method.GET, "http://www.sharefb.com/statusApp/readStatus.php?table_name="+tableName+"&time="+String.valueOf(time),
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray array = new JSONArray(s);
                            for (int i=0; i<array.length(); i++) {
                                Status status = new Status();
                                JSONObject object = array.getJSONObject(i);
                                status.setId(object.getInt("id"));
                                status.setStatus(object.getString("text"));
                                statusList.add(status);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            statusAdapter = new StatusAdapter(StatusListActivity.this,statusList);
                            recyclerView.setAdapter(statusAdapter);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(StatusListActivity.this,""+volleyError.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
        request.setShouldCache(false);
        MySingleton.getInstance(StatusListActivity.this).addToRequestQueue(request);

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        StatusListActivity.this.setTitle(R.string.app_name);
    }

    private void prepareInterstitialAds() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial));
        AdRequest adRequest2 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest2);
    }

    private boolean showInterstitialAd() {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    StatusListActivity.this.finish();
                }
            });
            return  true;
        }
        return  false;
    }

    @Override
    public void onBackPressed() {
        if (!showInterstitialAd()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.toolbar_menu:
                Intent intent = new Intent(StatusListActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.toolbar_share:
                Helper.share(StatusListActivity.this,Helper.APP_LINK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

}
