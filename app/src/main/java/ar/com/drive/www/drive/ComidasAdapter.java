package ar.com.drive.www.drive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ComidasAdapter extends BaseAdapter {

    Activity context;
    ArrayList<Comercio> comercios;
    Usuario usuario;
    private static LayoutInflater inflater = null;


    public ComidasAdapter(Activity context, ArrayList<Comercio> comercios, Usuario usuario){
        this.context = context;
        this.comercios = comercios;
        this.usuario = usuario;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return comercios.size();
    }

    @Override
    public Comercio getItem(int position) {
        return comercios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.list_comercios, null): itemView;
        Button btn = (Button) itemView.findViewById(R.id.btn_comida);
        ImageView img = (ImageView) itemView.findViewById(R.id.img);
        TextView nombre = (TextView) itemView.findViewById(R.id.nombre);
        TextView descripcion = (TextView) itemView.findViewById(R.id.descipcion);
        final Comercio selectedComercio = comercios.get(position);
        nombre.setText(selectedComercio.getNombre());
        descripcion.setText(selectedComercio.getDescripcion());
        Picasso.get().load(selectedComercio.getUrl_img()).into(img);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductosActivity.class);
                i.putExtra("usuario", usuario);
                i.putExtra("nombre", selectedComercio.getNombre());
                i.putExtra("descripcion", selectedComercio.getDescripcion());
                i.putExtra("direccion", selectedComercio.getDireccion());
                i.putExtra("url_img", selectedComercio.getUrl_img());
                i.putExtra("idComercio", selectedComercio.getId());
                context.startActivity(i);
            }
        });

        return itemView;
    }
}
