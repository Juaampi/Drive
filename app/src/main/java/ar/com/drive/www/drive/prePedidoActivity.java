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

public class prePedidoActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    Producto producto = new Producto();
    ArrayList<prePedido> pedidos = new ArrayList<>();
    private PrePedidoAdapter adapter;
    ListView lista;
    prePedido pedido = new prePedido();
    Usuario usuario = new Usuario();
    TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_pedido);
        pedido = (prePedido) getIntent().getSerializableExtra("prePedido");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        request = Volley.newRequestQueue(getApplicationContext());
        lista = (ListView) findViewById(R.id.listprePedido);
        cargarListaPrepedidos(pedido.getIdCliente());
        total = (TextView) findViewById(R.id.total);
    }

    private void cargarListaPrepedidos(int idCliente) {
        String url = "http://192.168.0.69/webservice/prePedido.php?idCliente="+usuario.getId();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        request.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Tenes que seleccionar algun producto!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray json = response.optJSONArray("prePedido");
        JSONObject jsonObject = null;
        double precio = 0;

        try {
            int i = 0;
            for(i=0; i<json.length();i++){

                prePedido PrePedido = new prePedido();
                jsonObject = json.getJSONObject(i);
                PrePedido.setNombre_producto(jsonObject.optString("nombre"));
                PrePedido.setPrecio(jsonObject.optDouble("precio"));
                precio = jsonObject.optDouble("precio")+precio;
                pedidos.add(PrePedido);
            }
            adapter = new PrePedidoAdapter(this, pedidos, usuario);
            lista.setAdapter(adapter);
            total.setText("El total es: $"+String.valueOf(precio));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
