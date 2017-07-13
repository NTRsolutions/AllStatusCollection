package com.sairajen.allstatuscollection.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class EnglishFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;

    private List<HomeMenu> menuList;
    private MenuAdapter homeAdapter;

    public EnglishFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        String[] title = getResources().getStringArray(R.array.menu_title_english);
        String[] table = getResources().getStringArray(R.array.menu_table_english);
        int[] thumbnail = {R.drawable.i1,R.drawable.i2,R.drawable.i18,R.drawable.i5,R.drawable.i3,R.drawable.i4,R.drawable.i6, R.drawable.i7,
                R.drawable.i11,R.drawable.i15,R.drawable.i19,R.drawable.i8,R.drawable.i13,R.drawable.i15,R.drawable.i8,
                R.drawable.i5,R.drawable.i4,R.drawable.cool_apps,R.drawable.ic_launcher};

        menuList = new ArrayList<>();

        for (int i=0; i<title.length; i++) {
            HomeMenu menu = new HomeMenu();
            menu.setTitle(title[i]);
            menu.setThumbnail(thumbnail[i]);
            menu.setTableName(table[i]);
            menuList.add(menu);
        }

        homeAdapter = new MenuAdapter(getActivity(),menuList,"english");
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();

        return view;
    }

}
