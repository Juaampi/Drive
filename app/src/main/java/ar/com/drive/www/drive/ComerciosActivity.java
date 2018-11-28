package ar.com.drive.www.drive;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

public class ComerciosActivity extends AppCompatActivity {

Toolbar myToolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comercios);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Usuario usuario = new Usuario();
        usuario.setNombre(getIntent().getStringExtra("nombre"));
        usuario.setApellido(getIntent().getStringExtra("apellido"));
        usuario.setDireccion(getIntent().getStringExtra("direccion"));
        usuario.setEmail(getIntent().getStringExtra("email"));
        usuario.setTelefono(getIntent().getStringExtra("telefono"));




    }


}


