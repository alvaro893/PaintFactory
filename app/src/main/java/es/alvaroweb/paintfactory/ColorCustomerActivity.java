package es.alvaroweb.paintfactory;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        int customerIndex = extras.getInt(Arguments.CUSTOMER_ARG);
        int caseIndex = extras.getInt(Arguments.CASE_ARG);
        customer = CaseSet.getInstance().getCase(caseIndex).getCustomers().get(customerIndex);

        colorAdapter = new ColorAdapter(this, customer.getSetOfPaints());
        ListView colorListView = (ListView) findViewById(R.id.color_list_view);
        colorListView.setAdapter(colorAdapter);
    }

    public void clickAddButton(View view) throws InvalidColorTypeException {
        List<Paint> paints = customer.getSetOfPaints();
        paints.add(new Paint(paints.size() + 1, 0));
        colorAdapter.notifyDataSetChanged();
    }

    public void clickCheckButton(View view){
        onBackPressed();
    }

}
