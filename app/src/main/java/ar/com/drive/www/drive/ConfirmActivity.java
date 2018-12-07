package ar.com.drive.www.drive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

public class ConfirmActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    JsonObjectRequest request;
    Button btn_confirm, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        //btn_confirm = (Button) findViewById(R.id.confirm_pedido);
       // btn_cancel = (Button) findViewById(R.id.cancel_pedido);

    }
}
