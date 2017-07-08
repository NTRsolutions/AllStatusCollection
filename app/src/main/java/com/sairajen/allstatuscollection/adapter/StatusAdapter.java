package com.sairajen.allstatuscollection.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sairajen.allstatuscollection.R;
import com.sairajen.allstatuscollection.StatusActivity;
import com.sairajen.allstatuscollection.model.Status;
import java.io.Serializable;
import java.util.List;

/**
 * @author Gmonetix
 */

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder>{

    private Context context;
    private List<Status> statusList;

    private static final String INTENT_STATUS = "intent_status";
    private static final String INTENT_STATUS_LIST = "intent_status_list";
    private static final String INTENT_STATUS_POS = "intent_status_pos";

    public StatusAdapter(Context context, List<Status> statusList) {
        this.context = context;
        this.statusList = statusList;
    }

    @Override
    public StatusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_row,parent,false);
        return new StatusAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StatusAdapter.MyViewHolder holder, final int position) {
        holder.textView.setText(statusList.get(position).getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StatusActivity.class);
                intent.putExtra(INTENT_STATUS,statusList.get(position).getStatus());
                intent.putExtra(INTENT_STATUS_LIST, (Serializable) statusList);
                intent.putExtra(INTENT_STATUS_POS,position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        protected TextView textView;
        protected CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.status_row_tv);
            cardView = (CardView) view.findViewById(R.id.status_row_cardview);
        }
    }

}
