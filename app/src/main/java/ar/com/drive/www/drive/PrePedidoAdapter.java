package ar.com.drive.www.drive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class PrePedidoAdapter extends BaseAdapter implements Response.Listener<JSONObject>, Response.ErrorListener {

    JsonObjectRequest jsonObjectRequest;
    RequestQueue request;
    Activity context;
    ArrayList<prePedido> pedidos;
    Usuario usuario;
    private static LayoutInflater inflater = null;

    public PrePedidoAdapter(Activity context, ArrayList<prePedido> pedidos, Usuario usuario){
        this.context = context;
        this.pedidos = pedidos;
        this.usuario = usuario;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return pedidos.size();
    }

    @Override
    public prePedido getItem(int position) {
        return pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.list_prepedido, null) : itemView;
        ImageButton btn_add = (ImageButton) itemView.findViewById(R.id.addPedido);
        ImageButton btn_delete = (ImageButton) itemView.findViewById(R.id.deletePedido);
        TextView nombre = (TextView) itemView.findViewById(R.id.nombre_producto);
        TextView precio = (TextView) itemView.findViewById(R.id.precio_producto);
        TextView total = (TextView) itemView.findViewById(R.id.total);
        final prePedido selectedPrePedido = pedidos.get(position);
        nombre.setText(selectedPrePedido.getNombre_producto());
        precio.setText("$"+String.valueOf(selectedPrePedido.getPrecio())+"0");
        request = Volley.newRequestQueue(context);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    cargarMismoPedido(selectedPrePedido.getIdCliente(), selectedPrePedido.getIdRestaurant(), selectedPrePedido.getIdProducto());


                }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePedido(selectedPrePedido.getId());
            }
        });

        return itemView;
    }

    private void deletePedido(int id) {
        String url = "http://192.168.0.69/webservice/deletePrePedido.php?id="+id;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "Eliminado con éxito!", Toast.LENGTH_SHORT).show();
                context.finish();
                context.startActivity(context.getIntent());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jsonObjectRequest);
    }

    private void cargarMismoPedido(int idCliente, int idRestaurant, int idProducto) {
        String url = "http://192.168.0.69/webservice/preProducto.php?idCliente="+idCliente+"&idComercio="+idRestaurant+"&idProducto="+idProducto;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(context, "¡Pedido agregado con éxito!", Toast.LENGTH_SHORT).show();
        context.finish();
        context.startActivity(context.getIntent());
    }
}
