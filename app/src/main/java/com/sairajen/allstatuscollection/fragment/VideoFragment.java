package com.sairajen.allstatuscollection.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sairajen.allstatuscollection.R;
import com.sairajen.allstatuscollection.adapter.MenuAdapter;
import com.sairajen.allstatuscollection.model.HomeMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gmonetix
 */

public class VideoFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;

    private List<HomeMenu> menuList;
    private MenuAdapter homeAdapter;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        String[] title = getResources().getStringArray(R.array.menu_title_video);
        String[] table = getResources().getStringArray(R.array.menu_table_video);
        int[] thumbnail = {R.drawable.i14,R.drawable.i11,R.drawable.i5,R.drawable.i12,R.drawable.i18,R.drawable.i11,R.drawable.i7,
                R.drawable.i2,R.drawable.i17,R.drawable.i20, R.drawable.i12,R.drawable.i12,R.drawable.i16,R.drawable.i16,
                R.drawable.i5,R.drawable.i15,R.drawable.i11,R.drawable.cool_apps,R.drawable.ic_launcher};

        menuList = new ArrayList<>();

        for (int i=0; i<title.length; i++) {
            HomeMenu menu = new HomeMenu();
            menu.setTitle(title[i]);
            menu.setThumbnail(thumbnail[i]);
            menu.setTableName(table[i]);
            menuList.add(menu);
        }

        homeAdapter = new MenuAdapter(getActivity(),menuList,"video");
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();

        return view;
    }

}