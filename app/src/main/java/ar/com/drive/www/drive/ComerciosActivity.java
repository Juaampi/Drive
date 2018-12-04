package ar.com.drive.www.drive;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

import java.io.Serializable;

public class ComerciosActivity extends AppCompatActivity implements Serializable {

    Button btn_comida, btn_bebida, btn_mercado, btn_tienda, btn_entregas, btn_personalizado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comercios);
        btn_comida = (Button) findViewById(R.id.btn_comida);
        btn_bebida = (Button) findViewById(R.id.btn_bebida);
        btn_mercado = (Button) findViewById(R.id.btn_mercado);
        btn_tienda = (Button) findViewById(R.id.btn_tienda);
        btn_entregas = (Button) findViewById(R.id.btn_entregas);
        btn_personalizado = (Button) findViewById(R.id.btn_personalizado);
        final Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        btn_comida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComerciosActivity.this, ComidasActivity.class);
                i.putExtra("usuario", usuario);
                startActivity(i);
            }
        });

    }

}


