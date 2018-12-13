package ar.com.drive.www.drive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Button btn_pedir;
    TextView name_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btn_pedir = (Button) findViewById(R.id.btn_pedir);
        name_profile = (TextView) findViewById(R.id.name_profile);
        final Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        name_profile.setText(usuario.getNombre()+" "+usuario.getApellido());

        btn_pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ProfileActivity.this, ComerciosActivity.class);
                i.putExtra("usuario", usuario);
                startActivity(i);

            }
        });
    }


}
