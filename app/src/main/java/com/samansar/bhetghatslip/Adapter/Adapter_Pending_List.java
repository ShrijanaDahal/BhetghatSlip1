package com.samansar.bhetghatslip.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samansar.bhetghatslip.Model.Model_RejectionList;
import com.samansar.bhetghatslip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/9/18.
 */

public class Adapter_Pending_List extends RecyclerView.Adapter<Adapter_Pending_List.MyViewHolder> {

    private List<Model_RejectionList> model_rejectionLists;
    private Context context;



    public Adapter_Pending_List(ArrayList<Model_RejectionList> model_rejectionLists, Context context) {
        this.model_rejectionLists = model_rejectionLists;
        this.context = context;
    }

    @Override
    public Adapter_Pending_List.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.approve_pending_items, parent, false);
        return new MyViewHolder(itemVIew, context);
    }

    @Override
    public void onBindViewHolder(Adapter_Pending_List.MyViewHolder holder, int position) {
        final Model_RejectionList model_approve = model_rejectionLists.get(position);
        holder.name.setText(model_approve.getName());
        holder.subject.setText(model_approve.getSubject());
        holder.description.setText(model_approve.getDescription());
        holder.contact.setText(model_approve.getContact());
        holder.address.setText(model_approve.getAddress());
        holder.email.setText(model_approve.getEmail());
        holder.date.setText(model_approve.getDate());
    }

    @Override
    public int getItemCount() {
        return model_rejectionLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,subject,description,contact,address,email,date;


        public MyViewHolder(View itemView, final Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.rejection_name);
            subject = itemView.findViewById(R.id.rejection_subject);
            description = itemView.findViewById(R.id.rejection_description);
            contact = itemView.findViewById(R.id.rejection_contact);
            address = itemView.findViewById(R.id.rejection_address);
            email = itemView.findViewById(R.id.rejection_email);
            date = itemView.findViewById(R.id.rejection_date);



        }
    }
}

