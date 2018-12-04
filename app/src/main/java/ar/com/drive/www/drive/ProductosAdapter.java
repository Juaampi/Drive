package ar.com.drive.www.drive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductosAdapter extends BaseAdapter implements Response.Listener<JSONObject>, Response.ErrorListener {

    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;
    Activity context;
    ArrayList<Producto> productos;
    Usuario usuario;
    private static LayoutInflater inflater = null;

    public ProductosAdapter(Activity context, ArrayList<Producto> productos, Usuario usuario){
        this.context = context;
        this.productos = productos;
        this.usuario = usuario;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return productos.size();
    }

    @Override
    public Producto getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.list_productos, null) : itemView;
        Button btn = (Button) itemView.findViewById(R.id.btn_producto);
        ImageView img = (ImageView) itemView.findViewById(R.id.imgproducto);
        TextView nombre = (TextView) itemView.findViewById(R.id.nombreproducto);
        TextView descripcion = (TextView) itemView.findViewById(R.id.descripcionproducto);
        TextView precio = (TextView) itemView.findViewById(R.id.precioproducto);
        final Producto selectedProducto = productos.get(position);
        nombre.setText(selectedProducto.getNombre());
        descripcion.setText(selectedProducto.getDescripcion());
        //precio.setText((int) selectedProducto.getPrecio());
        Picasso.get().load(selectedProducto.getUrl_img()).into(img);
        request = Volley.newRequestQueue(context);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarPedido(usuario.getId(), selectedProducto.getIdComercio(), selectedProducto.getId());
            }
        });

        return itemView;
    }

    private void cargarPedido(int idCliente, int idComercio, int idProducto) {

        String url = "http://192.168.0.69/webservice/preProducto.php?idCliente="+idCliente+"&idComercio="+idComercio+"&idProducto="+idProducto;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, "Error por favor intente nuevamente.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray json = response.optJSONArray("pedido");
        JSONObject jsonObject = null;

        try {
                final prePedido pedido = new prePedido();
                jsonObject = json.getJSONObject(0);
                pedido.setIdProducto(jsonObject.optInt("id"));
                pedido.setIdRestaurant(jsonObject.optInt("idComercio"));
                pedido.setIdCliente(jsonObject.optInt("idCliente"));
                Intent i = new Intent(context, prePedido.class);
                i.putExtra("prePedido", pedido);
                context.startActivity(i);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
