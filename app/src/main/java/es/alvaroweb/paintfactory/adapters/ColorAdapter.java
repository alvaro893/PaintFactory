package es.alvaroweb.paintfactory.adapters;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import es.alvaroweb.paintfactory.R;
import paintfactory.prototype.ColorType;
import paintfactory.prototype.Paint;

/**
 * Created by Alvaro on 13/04/2016.
 */
public class ColorAdapter extends BaseAdapter {

    private static final String MATTE = "matte";
    private static final String GLOSSY = "glossy";
    private List<Paint> paints;
    private Context context;
    private LayoutInflater layoutInflater;

    public ColorAdapter(Context context, List<Paint> paints) {
        this.context = context;
        this.paints = paints;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return paints.size();
    }

    @Override
    public Object getItem(int position) {
        return paints.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View targetView = convertView;
        if(targetView == null){
            targetView = this.layoutInflater.inflate(R.layout.paint_item, parent, false);
        }
        Paint paint = paints.get(position);
        if(paint != null){
            setItemViews(position, paint, targetView);
        }
        return targetView;
    }

    private void setItemViews(final int position, final Paint paint, View targetView) {
        TextView paintNumber = (TextView) targetView.findViewById(R.id.paint_number);
        paintNumber.setText("Paint " + (position + 1));

        final TextView paintType = (TextView) targetView.findViewById(R.id.paint_type);

        //set switch
        SwitchCompat paintSwitch = (SwitchCompat) targetView.findViewById(R.id.paint_type_switcher);
        if(paint.getType() == ColorType.MATTE){
            paintSwitch.setChecked(true);
            paintType.setText(MATTE);
        }
        paintSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    paintType.setText(MATTE);
                    paint.setType(ColorType.MATTE);
                } else{
                    paintType.setText(GLOSSY);
                    paint.setType(ColorType.GLOSSY);
                }
            }
        });

    }
}
