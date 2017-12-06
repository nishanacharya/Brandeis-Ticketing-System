package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class QRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final Button button_generate = (Button) findViewById(R.id.main_generate);
        final Button button_read = (Button) findViewById(R.id.main_read);

        button_generate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generate_QR(v);
            }
        });
        button_read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                read_QR(v);
            }
        });
    }

    public void generate_QR(View view) {
        Intent intent_generate = new Intent(this, QRGenerator.class);
        startActivity(intent_generate);
    }

    public void read_QR(View view) {
        Intent intent_read = new Intent(this, QRReader.class);
        startActivity(intent_read);
    }

    public String test(String test){ return test; }
}
