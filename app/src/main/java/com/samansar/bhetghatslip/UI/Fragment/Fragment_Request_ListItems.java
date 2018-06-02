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
import com.samansar.bhetghatslip.Adapter.Adapter_Request_ListItems;
import com.samansar.bhetghatslip.Listener.OnAdapterListener;
import com.samansar.bhetghatslip.Model.Model_Pending;
import com.samansar.bhetghatslip.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Request_ListItems extends Fragment{

    private ArrayList<Model_Pending> model_pendings;
    private RecyclerView recyclerView;
    private Adapter_Request_ListItems adapter_requestListItems;
    private String id, name,subject;
    private OnAdapterListener onAdapterListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_request_recycler,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview();
    }


    public void findview() {

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

        getData();
        onAdapterListeners();
    }


    private void getData() {
        GokarnaBista service = (GokarnaBista) RetrofitHelper.getInstance().getService(GokarnaBista.class);
        Call<JsonElement> call = service.getRequestList();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    String responseValue = response.body().toString();
                    JSONArray notification = null;
                    try {
                        JSONObject jsonObject = new JSONObject(responseValue);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        model_pendings = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject elem = jsonArray.getJSONObject(i);
                            Log.d("thisiselme",elem.toString());
                            id = elem.getString("id");
                            name = elem.getString("name");
                            subject = elem.getString("subject");

                            model_pendings.add(new Model_Pending(id,name,subject));


                        }

                        adapter_requestListItems = new Adapter_Request_ListItems(model_pendings, onAdapterListener, getContext());
                        recyclerView.setAdapter(adapter_requestListItems);
                        Log.d("datajsonarray", "this is the value of json " + notification);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else {
                    Toast.makeText(getContext(), "Empty Data", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });

    }

    public void onAdapterListeners() {
        onAdapterListener = new OnAdapterListener() {

            @Override
            public void onItemClicked(String id) {
                Fragment_Request_Decision fragment_requestDecision = new Fragment_Request_Decision();
                Bundle bd = new Bundle();
                bd.putString("id", id);
                fragment_requestDecision.setArguments(bd);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager != null ? fragmentManager.beginTransaction() : null;
                assert fragmentTransaction != null;
                fragmentTransaction.replace(R.id.main_container, fragment_requestDecision).addToBackStack("back").commit();
            }
        };
    }


}
