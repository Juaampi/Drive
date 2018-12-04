package ar.com.drive.www.drive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class prePedidoActivity extends AppCompatActivity {

    TextView id;
    prePedido pedido = new prePedido();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_pedido);

        id = (TextView) findViewById(R.id.idProducto);
        pedido = (prePedido) getIntent().getSerializableExtra("prePedido");
        Toast.makeText(getApplicationContext(), "texto", Toast.LENGTH_SHORT).show();
        id.setText(String.valueOf(pedido.getIdProducto()));

    }


}
