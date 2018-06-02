package com.samansar.bhetghatslip.UI.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.samansar.bhetghatslip.API.GokarnaBista;
import com.samansar.bhetghatslip.API.RetrofitHelper;
import com.samansar.bhetghatslip.CustomDialogueBox.Approve_Dialogue;
import com.samansar.bhetghatslip.Listener.OnAdapterListener;
import com.samansar.bhetghatslip.Listener.OnDialogEventListener;
import com.samansar.bhetghatslip.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Request_Decision extends Fragment {

    private TextView name,subject,description,contact,address,email,date;
    private String plname,plsubject,pldescription,plcontact,pladdress,plemail,pldate;
    private Button approve,pending;
    private Approve_Dialogue approve_dialogue1;
    private String sMeetingDate, sStartingTime, sEndingTime,current_id, id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_request_decision,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getString("id");
        initi();

        sendDataPending();

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataApprove();
            }
        });
    }

    public void initi(){
        name = getView().findViewById(R.id.pendinglist_name);
        subject = getView().findViewById(R.id.pendinglist_subject);
        description = getView().findViewById(R.id.pendinglist_description);
        contact = getView().findViewById(R.id.pendinglist_contact);
        address = getView().findViewById(R.id.pendinglist_address);
        email = getView().findViewById(R.id.pendinglist_email);
        date = getView().findViewById(R.id.pendinglist_date);
        approve = getView().findViewById(R.id.btn_approve);
        pending = getView().findViewById(R.id.btn_pending);

        getdata();

    }

    private void getdata() {

        GokarnaBista service = (GokarnaBista) RetrofitHelper.getInstance().getService(GokarnaBista.class);
        Call<JsonElement> call = service.getRequestList();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    String responseValue = response.body().toString();
                    try {
                        JSONObject jsonObject = new JSONObject(responseValue);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject elem = jsonArray.getJSONObject(i);
                            Log.d("thisiselme",elem.toString());
                            current_id=elem.getString("id");
                            String sid = elem.getString("id");
                            if(id.equals(sid)){
                                plname = elem.getString("name");
                                plsubject = elem.getString("subject");
                                pldescription = elem.getString("description");
                                plcontact = elem.getString("contact");
                                pladdress = elem.getString("address");
                                plemail = elem.getString("email");
                                pldate = elem.getString("date");
                            }

                        }
                        name.setText(plname);
                        subject.setText(plsubject);
                        description.setText(pldescription);
                        contact.setText(plcontact);
                        address.setText(pladdress);
                        email.setText(plemail);
                        date.setText(pldate);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else {
                    Toast.makeText(getContext(), "response fail", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

    }


    public void sendDataApprove(){
                approve_dialogue1 = new Approve_Dialogue(getContext(), new OnDialogEventListener() {
                    @Override
                    public void onDisAgreed() {
                    }

                    @Override
                    public void onAgreed(String meet_date, String start_time, String end_time) {

                        sMeetingDate = meet_date;
                        sStartingTime = start_time;
                        sEndingTime = end_time;
                        sendData();

//                        Toast.makeText(getContext(),"df="+mdd+"stt="+stt+"ett"+ett,Toast.LENGTH_SHORT).show();
                    }

                });
        approve_dialogue1.show();
        approve_dialogue1.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

//  Creating method to approve the data
    public  void sendData(){
        final String status = "1";
        String meetingDt, startingTm, endingTm, CurrentTm;
        meetingDt = sMeetingDate;
        startingTm = sStartingTime;
        endingTm = sEndingTime;
        CurrentTm = current_id;

        GokarnaBista gbService = (GokarnaBista) RetrofitHelper.getInstance().getService(GokarnaBista.class);
        Call<JsonElement> call2 = gbService.sendDataApprove(status, meetingDt, startingTm, endingTm, CurrentTm);
        call2.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()== true){
                    Toast.makeText(getContext(), "Data Send Successful", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "response fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }



    public void sendDataPending(){
        final String status  = "2";
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GokarnaBista service = (GokarnaBista) RetrofitHelper.getInstance().getService(GokarnaBista.class);
                Call<JsonElement> call = service.sendDataRejection(id,status);
                call.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Data Send Sucessfully", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getContext(), "response fail", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {

                    }
                });
            }
        });
    }

}
