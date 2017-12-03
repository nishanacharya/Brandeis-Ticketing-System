package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.zxing.Result;


import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by rebeccashi on 12/2/17.
 */

public class QRReader extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView ScannerView;
    final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_reader);

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CODE);
        }

        Button button = (Button) findViewById(R.id.read);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ScannerView = new ZXingScannerView(QRReader.this);
                setContentView(ScannerView);
                ScannerView.setResultHandler(QRReader.this);
                ScannerView.startCamera();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }

    public void handleResult(Result result) {
        // Do something with the result
        Log.w("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Resumes scanning
        ScannerView.resumeCameraPreview(this);
    }
}
