package ar.com.drive.www.drive;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ComidasActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener { 
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    private ArrayList<Comercio> Comercios = new ArrayList<>();
    private ComidasAdapter adapter;
    private ListView listViewComida;
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comidas);
        request = Volley.newRequestQueue(getApplicationContext());
        listViewComida = (ListView) findViewById(R.id.list_comida);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        cargarWebService();
    }

    private void cargarWebService() {
        String url = "http://192.168.0.69/webservice/comidas.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Error" , Toast.LENGTH_SHORT);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = response.optJSONArray("comercios");
        JSONObject jsonObject = null;

        try {
            int i = 0;
            for(i=0; i<json.length();i++){
                Comercio comercio = new Comercio();
                jsonObject = json.getJSONObject(i);
                comercio.setId(jsonObject.optInt("id"));
                comercio.setNombre(jsonObject.optString("nombre"));
                comercio.setDireccion(jsonObject.optString("direccion"));
                comercio.setDescripcion(jsonObject.optString("descripcion"));
                comercio.setUrl_img(jsonObject.optString("img"));
                String urlImagen = "http://192.168.0.69/webservice/"+comercio.getUrl_img().toString();
                comercio.setUrl_img(urlImagen);
                Comercios.add(comercio);
            }
            adapter = new ComidasAdapter(this, Comercios, usuario);
            listViewComida.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
