package com.samansar.bhetghatslip.CustomDialogueBox;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.samansar.bhetghatslip.Listener.OnDialogEventListener;
import com.samansar.bhetghatslip.R;


public class Approve_Dialogue extends Dialog{
    private Context context;
    private OnDialogEventListener mListener;
    private EditText meetingDate, startingTime, endTime;
    private Button sendButton, cancelButton;

    public Approve_Dialogue(@NonNull Context context, OnDialogEventListener mListener) {
        super(context);
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.after_approve_dilogue_box);
        meetingDate = findViewById(R.id.meeting_date);
        startingTime = findViewById(R.id.start_time);
        endTime = findViewById(R.id.end_time);
        sendButton = findViewById(R.id.send);
        cancelButton = findViewById(R.id.cancel);
        setCancelable(false);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d_meetingDate = meetingDate.getText().toString();
                String d_startingTime = startingTime.getText().toString();
                String d_endTime= endTime.getText().toString();
                mListener.onAgreed(d_meetingDate,d_startingTime,d_endTime);

                dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
