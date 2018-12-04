package ar.com.drive.www.drive;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    Button btn_ingresar;
    EditText input_user, input_password;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);
       btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
       input_user = (EditText) findViewById(R.id.input_user);
       input_password = (EditText) findViewById(R.id.input_password);

        request = Volley.newRequestQueue(getApplicationContext());
        
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });

    }

    private void cargarWebService() {

        String url = "http://192.168.0.69/webservice/login.php?username="+input_user.getText().toString()+"&password="+input_password.getText().toString();
        url = url.replace(" ", "%20");

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        request.add(jsonObjectRequest);


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "No se encontró usuario", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray json = response.optJSONArray("usuario");
        JSONObject jsonObject = null;
        Usuario usuario = new Usuario();

        try {
            jsonObject = json.getJSONObject(0);
            Intent i = new Intent(LoginActivity.this, ComerciosActivity.class);
            usuario.setNombre(jsonObject.optString("name"));
            usuario.setApellido(jsonObject.optString("lastname"));
            usuario.setTelefono(jsonObject.optString("phone"));
            usuario.setId(jsonObject.optInt("id"));
            i.putExtra("usuario", usuario);
            startActivity(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
