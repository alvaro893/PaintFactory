package es.alvaroweb.paintfactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import es.alvaroweb.paintfactory.comunication.CaseSet;

/**
 * Created by Alvaro on 14/04/2016.
 */
public class CaseDialog extends Dialog {
    Activity activity;
    private int numberOfPaints;

    public CaseDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        numberOfPaints = 1;
        setTitle("PAINT");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.case_dialog);
        final TextView numberOfPaintsView = (TextView) findViewById(R.id.number_of_paints);

        ImageButton addPaintButton = (ImageButton) findViewById(R.id.add_paitn_button);
        addPaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfPaints++;
                numberOfPaintsView.setText(numberOfPaints + "");
            }
        });

        ImageButton removePaintButton = (ImageButton) findViewById(R.id.remove_paint_button);
        removePaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfPaints > 1){
                    numberOfPaints--;
                }
                numberOfPaintsView.setText(numberOfPaints + "");
            }
        });

        Button okButton = (Button) findViewById(R.id.dialog_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaseSet.getInstance().addCase(numberOfPaints);
                dismiss();
            }
        });
    }



}
