package ar.com.drive.www.drive;

import android.content.Intent;
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

import java.util.ArrayList;

public class ProductosActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    TextView titulo;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    Producto producto = new Producto();
    ArrayList<Producto> productos = new ArrayList<>();
    private ProductosAdapter adapter;
    ListView lista;
    int idComercio;
    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        lista = (ListView) findViewById(R.id.list_productos);
        request = Volley.newRequestQueue(getApplicationContext());
        idComercio = getIntent().getIntExtra("idComercio", 0);
        titulo = (TextView) findViewById(R.id.titulo);
        titulo.setText(getIntent().getExtras().getString("nombre"));
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        traerProductos(idComercio);
    }

    private void traerProductos(int idComercio) {
        String url = "http://192.168.0.69/webservice/productos.php?idComercio="+idComercio;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = response.optJSONArray("productos");
        JSONObject jsonObject = null;

        try {
            int i = 0;
            for(i=0; i<json.length();i++){
                Producto producto = new Producto();
                jsonObject = json.getJSONObject(i);
                producto.setId(jsonObject.optInt("id"));
                producto.setNombre(jsonObject.optString("nombre"));
                producto.setPrecio(jsonObject.optDouble("precio"));
                producto.setDescripcion(jsonObject.optString("descripcion"));
                producto.setUrl_img(jsonObject.optString("url"));
                producto.setIdComercio(jsonObject.optInt("idComercio"));
                String urlImagen = "http://192.168.0.69/webservice/productos/"+producto.getUrl_img().toString();
                producto.setUrl_img(urlImagen);
                productos.add(producto);
            }
            adapter = new ProductosAdapter(this, productos, usuario);
            lista.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
