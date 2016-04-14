package es.alvaroweb.paintfactory;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import es.alvaroweb.paintfactory.adapters.ColorAdapter;
import es.alvaroweb.paintfactory.comunication.CaseSet;
import paintfactory.prototype.ColorType;
import paintfactory.prototype.Customer;
import paintfactory.prototype.InvalidColorTypeException;
import paintfactory.prototype.Paint;

public class ColorCustomerActivity extends AppCompatActivity {

    private ColorAdapter colorAdapter;
    private Customer customer;
    private int caseIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        int customerIndex = extras.getInt(Arguments.CUSTOMER_ARG);
        caseIndex = extras.getInt(Arguments.CASE_ARG);
        customer = CaseSet.getInstance().getCase(caseIndex).getCustomers().get(customerIndex);

        colorAdapter = new ColorAdapter(this, customer.getSetOfPaints());
        ListView colorListView = (ListView) findViewById(R.id.color_list_view);
        colorListView.setAdapter(colorAdapter);
        colorListView.setOnItemLongClickListener(itemLongClick());
    }

    private AdapterView.OnItemLongClickListener itemLongClick() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Snackbar.make(view, "Do you want to delete it?", Snackbar.LENGTH_LONG)
                        .setAction("do it!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customer.getSetOfPaints().remove(position);
                                colorAdapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        };
    }

    public void clickAddButton(View view) throws InvalidColorTypeException {
        int numberOfPaints = CaseSet.getInstance().getCase(caseIndex).getNumberOfPaints();
        if(colorAdapter.getCount() >= numberOfPaints){
            Toast.makeText(this, "can't add more paints", Toast.LENGTH_LONG).show();
        }else{
            List<Paint> paints = customer.getSetOfPaints();
            paints.add(new Paint(paints.size() + 1, ColorType.GLOSSY));
            colorAdapter.notifyDataSetChanged();
        }

    }

    public void clickCheckButton(View view){
        onBackPressed();
    }

}
