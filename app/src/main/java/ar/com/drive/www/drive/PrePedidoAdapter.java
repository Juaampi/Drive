package ar.com.drive.www.drive;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PrePedidoAdapter extends BaseAdapter {

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
        TextView nombre = (TextView) itemView.findViewById(R.id.nombre_producto);
        TextView precio = (TextView) itemView.findViewById(R.id.precio_producto);
        TextView total = (TextView) itemView.findViewById(R.id.total);
        final prePedido selectedPrePedido = pedidos.get(position);
        nombre.setText(selectedPrePedido.getNombre_producto());
        precio.setText("$"+String.valueOf(selectedPrePedido.getPrecio()));

        return itemView;
    }
}
