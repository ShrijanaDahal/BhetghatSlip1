package com.samansar.bhetghatslip.UI.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
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
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.samansar.bhetghatslip.API.GokarnaBista;
import com.samansar.bhetghatslip.API.RetrofitHelper;
import com.samansar.bhetghatslip.Adapter.Adapter_Pending_List;
import com.samansar.bhetghatslip.CustomDialogueBox.Approve_Dialogue;
import com.samansar.bhetghatslip.Listener.ClickListener;
import com.samansar.bhetghatslip.Listener.OnAdapterListener;
import com.samansar.bhetghatslip.Listener.OnDialogEventListener;
import com.samansar.bhetghatslip.Listener.RecyclerTouchListener;
import com.samansar.bhetghatslip.Model.Model_RejectionList;
import com.samansar.bhetghatslip.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Pending_List extends Fragment{

    private ArrayList<Model_RejectionList> model_rejectionLists;
    private RecyclerView recyclerView;
    private Adapter_Pending_List adapter_pendingList;
    private String name,subject, email, description, contact, address, date, id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pending_recycler,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findview();

    }

    public void findview() {

        recyclerView = getView().findViewById(R.id.recyclerviewrecjtionList);
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

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

//                Model_RejectionList selectedLists = model_rejectionLists.get(position);
                Fragment_Request_Decision fragment_requestDecision = new Fragment_Request_Decision();
                Bundle bd = new Bundle();
                bd.putString("id", id);
                bd.putString("name", name);
                bd.putString("subject", subject);
                bd.putString("description", description);
                bd.putString("contact", contact);
                bd.putString("address", address);
                bd.putString("email", email);
                bd.putString("date", date);

//                bd.putParcelable("Lists", (Parcelable) selectedLists);
                fragment_requestDecision.setArguments(bd);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager != null ? fragmentManager.beginTransaction() : null;
                assert fragmentTransaction != null;
                fragmentTransaction.replace(R.id.main_container, fragment_requestDecision).addToBackStack("back").commit();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void getdata() {
        GokarnaBista service = (GokarnaBista) RetrofitHelper.getInstance().getService(GokarnaBista.class);
        Call<JsonElement> call = service.getRejectionList();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    String responseValue = response.body().toString();
                    JSONArray notification = null;
                    try {
                        JSONObject jsonObject = new JSONObject(responseValue);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        model_rejectionLists = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject elem = jsonArray.getJSONObject(i);
                            Log.d("thisiselme",elem.toString());
                            name = elem.getString("name");
                            subject = elem.getString("subject");
                            description = elem.getString("description");
                            contact = elem.getString("contact");
                            address = elem.getString("address");
                            email = elem.getString("email");
                            date = elem.getString("date");
                            //connectToAdapter(title,body);

                            model_rejectionLists.add(new Model_RejectionList(id,name, subject, description, contact, address, email, date));

                        }

                        adapter_pendingList = new Adapter_Pending_List(model_rejectionLists, getContext());
                        recyclerView.setAdapter(adapter_pendingList);
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
