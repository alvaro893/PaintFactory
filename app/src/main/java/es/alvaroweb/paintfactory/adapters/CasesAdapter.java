package es.alvaroweb.paintfactory.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import es.alvaroweb.paintfactory.R;
import es.alvaroweb.paintfactory.comunication.CaseSet;
import paintfactory.prototype.Case;

/**
 * Created by Alvaro on 13/04/2016.
 */
public class CasesAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private Context context;
    private CaseSet caseSet;

    public CasesAdapter(Context context) {
        caseSet = CaseSet.getInstance();
        this.context = context;
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return caseSet.getCases().size();
    }

    @Override
    public Object getItem(int i) {
        return caseSet.getCase(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View targetView = convertView;
        if(targetView == null){
            targetView = this.layoutInflater.inflate(R.layout.case_item, parent, false);
        }
        Case aCase = caseSet.getCase(position);
        if(aCase != null){
            setItemViews(position, aCase, targetView);
        }
        return targetView;
    }

    private void setItemViews(int i, final Case aCase, View targetView){
        TextView caseTextView = (TextView) targetView.findViewById(R.id.case_number);
        caseTextView.setText("Case " + (i + 1));

        final TextView numberOfPaints = (TextView) targetView.findViewById(R.id.number_of_paints);

        ImageButton addPaintButton = (ImageButton) targetView.findViewById(R.id.add_paitn_button);
        addPaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(numberOfPaints.getText().toString());
                n++;
                numberOfPaints.setText(n+"");
            }
        });

        ImageButton removePaintButton = (ImageButton) targetView.findViewById(R.id.remove_paint_button);
        removePaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(numberOfPaints.getText().toString());
                n--;
                numberOfPaints.setText(n+"");
            }
        });
    }
}
