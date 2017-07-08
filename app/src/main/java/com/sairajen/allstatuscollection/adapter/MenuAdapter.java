package com.sairajen.allstatuscollection.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sairajen.allstatuscollection.R;
import com.sairajen.allstatuscollection.StatusListActivity;
import com.sairajen.allstatuscollection.VideoListActivity;
import com.sairajen.allstatuscollection.model.HomeMenu;
import com.sairajen.allstatuscollection.utils.Helper;

import java.util.List;

/**
 * @author Gmonetix
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>{

    private Context context;
    private List<HomeMenu> menuList;
    private String language;

    private final static String TABLE_NAME = "table_name";

    public MenuAdapter(Context context, List<HomeMenu> menuList, String language) {
        this.context = context;
        this.menuList = menuList;
        this.language = language;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.menuThumbnail.setImageDrawable(context.getResources().getDrawable(menuList.get(position).getThumbnail()));
        holder.menuTitle.setText(menuList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (language.equals("video")) {
                    if (menuList.get(position).getTableName().equals("null")) {
                        Helper.openLink(context,Helper.COOL_APP_LINK);
                    } else if(menuList.get(position).getTableName().equals("share")) {
                        Helper.share(context,Helper.APP_LINK);
                    } else {
                        Intent intent = new Intent(context, VideoListActivity.class);
                        intent.putExtra(TABLE_NAME,menuList.get(position).getTableName());
                        context.startActivity(intent);
                        Toast.makeText(context,"Video",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (menuList.get(position).getTableName().equals("null")) {
                        Helper.openLink(context,Helper.COOL_APP_LINK);
                    } else if(menuList.get(position).getTableName().equals("share")) {
                        Helper.share(context,Helper.APP_LINK);
                    } else {
                        Intent intent = new Intent(context, StatusListActivity.class);
                        intent.putExtra(TABLE_NAME,language+"_"+menuList.get(position).getTableName());
                        context.startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        protected ImageView menuThumbnail;
        protected TextView menuTitle;

        public MyViewHolder(View view) {
            super(view);
            menuThumbnail = (ImageView) view.findViewById(R.id.homeMenuImage);
            menuTitle = (TextView) view.findViewById(R.id.homeMenuTitle);
        }
    }

}
