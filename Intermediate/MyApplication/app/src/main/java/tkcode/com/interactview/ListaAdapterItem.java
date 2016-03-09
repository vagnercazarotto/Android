package tkcode.com.interactview;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vagner on 09/03/16.
 */
public class ListaAdapterItem extends ArrayAdapter<item> {

    public Context context;
    private ArrayList<item> lista;

    public ListaAdapterItem(Context context,ArrayList<item> lista){
        super(context,0,lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        item itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item,null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        imageView.setImageResource(itemPosicao.getImagem());

        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(itemPosicao.getNome());

        TextView textViewDescricao = (TextView) convertView.findViewById(R.id.textView2);
        textView.setText(itemPosicao.getDescricao());

        return convertView;
    }



}
