package com.samansar.bhetghatslip.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samansar.bhetghatslip.Model.Model_After_Approve;
import com.samansar.bhetghatslip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/9/18.
 */

public class Adapter_After_Approve extends RecyclerView.Adapter<Adapter_After_Approve.MyViewHolder> {

    private List<Model_After_Approve> model_afterApproves;
    private Context context;

    public Adapter_After_Approve(ArrayList<Model_After_Approve> model_afterApproves, Context context) {
        this.model_afterApproves = model_afterApproves;
        this.context = context;

    }

    @Override
    public Adapter_After_Approve.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.after_approve_listitem, parent, false);
        return new MyViewHolder(itemVIew, context);
    }

    @Override
    public void onBindViewHolder(Adapter_After_Approve.MyViewHolder holder, int position) {
        final Model_After_Approve model_afterApprove = model_afterApproves.get(position);
        holder.name.setText(model_afterApprove.getName());
        holder.subject.setText(model_afterApprove.getSubject());
        holder.description.setText(model_afterApprove.getDescription());
        holder.contact.setText(model_afterApprove.getContact());
        holder.address.setText(model_afterApprove.getAddress());
        holder.email.setText(model_afterApprove.getEmail());
        holder.startingTime.setText(model_afterApprove.getStarting_time());
        holder.endTime.setText(model_afterApprove.getEnding_time());
        holder.meetingDate.setText(model_afterApprove.getMeeting_date());
    }

    @Override
    public int getItemCount() {
        return model_afterApproves.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,subject,description,contact,address,email,meetingDate,startingTime, endTime;

        public MyViewHolder(View itemView, final Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.list_name);
            subject = itemView.findViewById(R.id.list_subject);
            description = itemView.findViewById(R.id.list_description);
            contact = itemView.findViewById(R.id.list_contact);
            address = itemView.findViewById(R.id.list_address);
            email = itemView.findViewById(R.id.list_email);
            meetingDate = itemView.findViewById(R.id.meeting);
            startingTime = itemView.findViewById(R.id.starting);
            endTime = itemView.findViewById(R.id.end);
        }
    }
}

