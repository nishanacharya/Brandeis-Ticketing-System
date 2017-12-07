package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.Result;


import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by rebeccashi on 12/2/17.
 */

public class QRReader extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView ScannerView;
    final int REQUEST_CODE = 100;
    TicketDataHandler dh;
    private ArrayList<EventHolder> list;
    private String[] eventInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_reader);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CODE);
        }

        Intent receiveIntent = getIntent();
        Bundle extras = receiveIntent.getExtras();
        if(extras != null) {
            eventInfo = extras.getStringArray("eventInfo");
        } else {
            eventInfo = new String[]{};
        }

        // Open QR Code reader
        ScannerView = new ZXingScannerView(QRReader.this);
        setContentView(ScannerView);
        ScannerView.setResultHandler(QRReader.this);
        ScannerView.startCamera();

        dh = new TicketDataHandler(this);
        list = dh.getData(FirebaseAuth.getInstance().getCurrentUser().toString());

    }

    @Override
    public void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }

    public void handleResult(Result result) {
        // Do something with the result
        String scanResult = null;
        Log.w(getString(R.string.handleResult), result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.scanResult));

        String[] userAndTicket = result.getText().split("~");
        String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
//        String eventName = list.get(position).getUniqueEventId();   // position is index of event in db?
        scanResult = userAndTicket[1];
        builder.setMessage(scanResult);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Resumes scanning
        ScannerView.resumeCameraPreview(this);
    }
}
