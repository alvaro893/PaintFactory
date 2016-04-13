package es.alvaroweb.paintfactory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import es.alvaroweb.paintfactory.adapters.CustomerAdapter;
import es.alvaroweb.paintfactory.comunication.CaseSet;
import paintfactory.prototype.Case;
import paintfactory.prototype.Customer;
import paintfactory.prototype.Paint;

public class CustomerActivity extends AppCompatActivity {

    private CustomerAdapter customerAdapter;
    private Case aCase;
    private ListView customerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initializeCase();
        customerAdapter = new CustomerAdapter(this, aCase.getCustomers());

        customerListView = (ListView) findViewById(R.id.customer_list_view);
        customerListView.setAdapter(customerAdapter);
        customerListView.setOnItemClickListener(onItemClick());
        customerListView.setOnItemLongClickListener(onItemLongClick());

    }

    private void initializeCase() {
        int caseIndex = getIntent().getExtras().getInt(Arguments.CASE_ARG);
        aCase = CaseSet.getInstance().getCase(caseIndex);
    }

    public void clickAddButton(View view){
        aCase.getCustomers().add(new Customer(new ArrayList<Paint>()));
        customerAdapter.notifyDataSetChanged();
    }

    public void clickCheckButton(View view){

    }

    private AdapterView.OnItemClickListener onItemClick(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomerActivity.this, ColorCustomerActivity.class);
                Customer customer = (Customer) customerAdapter.getItem(position);
                // pass the extras from MainActivity
                intent.putExtras(getIntent().getExtras());
                intent.putExtra(Arguments.CUSTOMER_ARG, position);
                startActivity(intent);
            }
        };
    }


    private AdapterView.OnItemLongClickListener onItemLongClick() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
                //TODO: long clikck customer
            }
        };
    }


}
