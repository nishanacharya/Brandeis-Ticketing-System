package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button_generate = (Button) findViewById(R.id.main_generate);

        button_generate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                generate_QR(v);
            }
        });}
    public void generate_QR(View view) {
        Intent intent_generate = new Intent(this, QRGenerator.class);
        startActivity(intent_generate);
    }

    public String test(String test){ return test; }
}
