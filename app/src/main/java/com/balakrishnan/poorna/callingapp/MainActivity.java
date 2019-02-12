package com.balakrishnan.poorna.callingapp;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv,tv2;
    EditText ed2;
    Button b1;
    TelephonyManager tm;
    PhoneStateListener ls;
    private static final int MY_PERMISSION_REQUEST_CALL_PHONE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ls = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                if (TelephonyManager.CALL_STATE_IDLE == state) {
                    Toast.makeText(getApplicationContext(), "No call received or Call disconnected", Toast.LENGTH_LONG).show();
                } else if (TelephonyManager.CALL_STATE_RINGING == state) {
                    Toast.makeText(getApplicationContext(), "Phone ringing", Toast.LENGTH_LONG).show();
                } else if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                    Toast.makeText(getApplicationContext(), "Call received", Toast.LENGTH_LONG).show();
                }

            }
        };
        tm.listen(ls, PhoneStateListener.LISTEN_CALL_STATE);
        tv = findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);
        ed2 = findViewById(R.id.editText2);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Intent in = new Intent(Intent.ACTION_CALL);
                    String phnno=String.format("tel: %s",ed2.getText().toString());
                    in.setData(Uri.parse(phnno));

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                       Toast.makeText(getApplicationContext(), "Error in permission", Toast.LENGTH_LONG).show();
                       // ActivityCompat.requestPermissions((Activity) getApplicationContext(),new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSION_REQUEST_CALL_PHONE);
                    return;
                    }

                    startActivity(in);


            }
        });

    }
    @Override
    public void onClick(View v) {

    }

}
