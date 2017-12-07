package edu.brandeis.cs.nishanacharya.brandeisticketingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.w3c.dom.Text;

/**
 * Created by rebeccashi on 12/2/17.
 */

public class QRGenerator extends AppCompatActivity{
    ImageView imageView;
    public final static int QRcodeWidth = 500;
    Bitmap bitmap;
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String TicketID;
    String[] eventInfo;
    String convertToQR;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_generator);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        imageView = (ImageView)findViewById(R.id.imageView);
        //TextView qrID = (TextView) findViewById(R.id.uniqueID);
        Intent receiveIntent = getIntent();
        Bundle extras = receiveIntent.getExtras();
        if(extras != null) {
            TicketID = extras.getString("uniqueTicketID");
            eventInfo = extras.getStringArray("eventInfo");
        } else {
            eventInfo = new String[]{};
        }
        convertToQR = userID + "~" + TicketID;
        //qrID.setText(convertToQR);

        TextView name = findViewById(R.id.ticketName);
        TextView description = findViewById(R.id.ticketDescription);
        TextView location = findViewById(R.id.ticketLocation);
        TextView date = findViewById(R.id.ticketDate);
        TextView time = findViewById(R.id.ticketTime);
        name.setText(eventInfo[0]);
        description.setText(eventInfo[1]);
        location.setText(eventInfo[2]);
        date.setText(eventInfo[3]);
        time.setText(eventInfo[4]);


        try {
            bitmap = TextToImageEncode(convertToQR);

            imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu item) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.directory,item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_Button:
                Intent homeIntent = new Intent(this, HomeActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        ContextCompat.getColor(this.getApplicationContext(), android.R.color.black) : ContextCompat.getColor(this.getApplicationContext(), android.R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
