package ar.com.drive.www.drive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitActivity extends AppCompatActivity {

    Button btn_ingreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        btn_ingreso = (Button) findViewById(R.id.btn_ingreso);

        btn_ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
