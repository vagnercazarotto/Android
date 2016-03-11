package tkcode.com.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vagner on 09/03/16.
 */
class CustomAdapter extends ArrayAdapter<String>{

    public CustomAdapter(Context context, String[] foods) {
        super(context,R.layout.custom_row,foods);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);

        String simgleFoodItem = getItem(position);
        TextView buckyText = (TextView) view.findViewById(R.id.textView);
        ImageView buckImage = (ImageView) view.findViewById(R.id.imageView);

        buckyText.setText(simgleFoodItem);
        buckyText.setTextColor(Color.parseColor("#EED818"));
        //buckImage.setColorFilter(0x88);
        buckImage.setImageResource(R.drawable.vag);

        return view;
    }
}
