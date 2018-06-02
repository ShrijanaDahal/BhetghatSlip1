package com.samansar.bhetghatslip.UI.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.samansar.bhetghatslip.API.GokarnaBista;
import com.samansar.bhetghatslip.API.RetrofitHelper;
import com.samansar.bhetghatslip.Adapter.Adapter_After_Approve;
import com.samansar.bhetghatslip.Listener.OnAdapterListener;
import com.samansar.bhetghatslip.Listener.OnDialogEventListener;
import com.samansar.bhetghatslip.Model.Model_After_Approve;
import com.samansar.bhetghatslip.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApproveList extends Fragment{

    private ArrayList<Model_After_Approve> model_afterApproves;
    private RecyclerView recyclerView;
    private Adapter_After_Approve adapter_After_approve;
    String name,subject,description,contact,address,email,meeting_date, starting_time, ending_time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_request_recycler,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView();
    }


    public void findView() {

        recyclerView = getView().findViewById(R.id.recyclerviewgetrequest);
        init();
    }

    public void init() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        getdata();
//        OnDialogEventListener();

    }


    private void getdata() {
        GokarnaBista service = (GokarnaBista) RetrofitHelper.getInstance().getService(GokarnaBista.class);
        Call<JsonElement> call = service.getApproveList();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    String responseValue = response.body().toString();
                    JSONArray notification = null;
                    try {
                        JSONObject jsonObject = new JSONObject(responseValue);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        model_afterApproves = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject elem = jsonArray.getJSONObject(i);
                            Log.d("thisiselme",elem.toString());
                            email = elem.getString("email");
                            name = elem.getString("name");
                            subject = elem.getString("subject");
                            description = elem.getString("description");
                            contact = elem.getString("contact");
                            address = elem.getString("address");
                            starting_time = elem.getString("starting_time");
                            ending_time = elem.getString("ending_time");
                            meeting_date = elem.getString("meeting_date");

                            //connectToAdapter(title,body);

                            model_afterApproves.add(new Model_After_Approve(name,subject,description,contact,address,email,starting_time,ending_time,meeting_date));

                        }

                        adapter_After_approve = new Adapter_After_Approve(model_afterApproves, getContext());
                        recyclerView.setAdapter(adapter_After_approve);
                        Log.d("datajsonarray", "this is the value of json " + notification);

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

}


