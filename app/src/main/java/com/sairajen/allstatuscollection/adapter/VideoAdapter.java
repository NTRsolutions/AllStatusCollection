package com.sairajen.allstatuscollection.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sairajen.allstatuscollection.R;
import com.sairajen.allstatuscollection.model.Status;
import com.sairajen.allstatuscollection.utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Gmonetix
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder>{

    private Context context;
    private List<Status> videoList;

    private final static String thumbnail = "http://img.youtube.com/vi/VIDEO_ID/hqdefault.jpg";

    public VideoAdapter(Context context, List<Status> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @Override
    public VideoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row,parent,false);
        return new VideoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.MyViewHolder holder, final int position) {

        holder.textView.setText(videoList.get(position).getTitle());
        final String id = videoList.get(position).getStatus().substring(32);
        Picasso.with(context).load(thumbnail.replace("VIDEO_ID",id)).into(holder.imageView);

        holder.btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoList.get(position).getExtra().equals("")) {
                    Helper.shareViaFacebook(context,videoList.get(position).getStatus());
                } else {
                    Helper.shareViaFacebook(context,videoList.get(position).getExtra());
                }
            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoList.get(position).getExtra().equals("")) {
                    Helper.share(context,videoList.get(position).getStatus());
                } else {
                    Helper.share(context,videoList.get(position).getExtra());
                }
            }
        });

        holder.btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoList.get(position).getExtra().equals("")) {
                    Helper.shareViaTwitter(context,videoList.get(position).getStatus());
                } else {
                    Helper.shareViaTwitter(context,videoList.get(position).getExtra());
                }
            }
        });

        holder.btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoList.get(position).getExtra().equals("")) {
                    Helper.shareViaWhatsapp(context,videoList.get(position).getStatus());
                } else {
                    Helper.shareViaWhatsapp(context,videoList.get(position).getExtra());
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.watchYoutubeVideo(context,id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        protected ImageView imageView, btnFb, btnWhatsapp, btnTwitter, btnShare;
        protected TextView textView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.video_row_iv);
            btnFb = (ImageView) view.findViewById(R.id.video_row_iv_fb_share);
            btnWhatsapp = (ImageView) view.findViewById(R.id.video_row_iv_whatsapp_share);
            btnTwitter = (ImageView) view.findViewById(R.id.video_row_twitter_share);
            btnShare = (ImageView) view.findViewById(R.id.video_row_iv_share);
            textView = (TextView) view.findViewById(R.id.video_row_tv);
        }
    }

}
