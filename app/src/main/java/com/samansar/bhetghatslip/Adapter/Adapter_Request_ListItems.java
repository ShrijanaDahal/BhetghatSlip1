package com.samansar.bhetghatslip.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samansar.bhetghatslip.Listener.OnAdapterListener;
import com.samansar.bhetghatslip.Model.Model_Pending;
import com.samansar.bhetghatslip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/9/18.
 */

public class Adapter_Request_ListItems extends RecyclerView.Adapter<Adapter_Request_ListItems.MyViewHolder> {
    String id;
    private List<Model_Pending> model_pendings;
    private Context context;
    private OnAdapterListener onAdapterListener;


    public Adapter_Request_ListItems(ArrayList<Model_Pending> model_pendings, OnAdapterListener onAdapterListener, Context context) {
        this.model_pendings = model_pendings;
        this.context = context;
        this.onAdapterListener = onAdapterListener;
    }

    @Override
    public Adapter_Request_ListItems.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_request_list_items, parent, false);
        return new MyViewHolder(itemVIew, context);
    }

    @Override
    public void onBindViewHolder(Adapter_Request_ListItems.MyViewHolder holder, int position) {
        final Model_Pending model_pending = model_pendings.get(position);
        final String id = model_pending.getId();
        holder.name.setText(model_pending.getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getId(id);
            }
        });

        holder.mySubject.setText(model_pending.getSubject());
        holder.mySubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getId(id);

            }
        });
    }

    public void getId(String id) {
        onAdapterListener.onItemClicked(id);
    }


    @Override
    public int getItemCount() {
        return model_pendings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, mySubject;

        public MyViewHolder(View itemView, final Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.pending_name);
            mySubject = itemView.findViewById(R.id.pending_subject);

        }
    }
}

