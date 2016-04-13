package es.alvaroweb.paintfactory.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;
import java.util.zip.Inflater;

import es.alvaroweb.paintfactory.CustomerActivity;
import es.alvaroweb.paintfactory.R;
import es.alvaroweb.paintfactory.comunication.CaseSet;
import paintfactory.prototype.Case;
import paintfactory.prototype.Customer;

/**
 * Created by Alvaro on 13/04/2016.
 */
public class CustomerAdapter extends BaseAdapter {
    private final List<Customer> customers;
    private CaseSet caseSet;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomerAdapter(Context context, List<Customer> customers) {
        this.context = context;
        caseSet = CaseSet.getInstance();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.customers = customers;
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public Object getItem(int position) {
        return customers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View targetView = convertView;
        if(targetView == null){
            targetView = this.layoutInflater.inflate(R.layout.customer_item, parent, false);
        }
        Customer customer = customers.get(position);
        if(customer != null){
            setItemViews(position, customer, targetView);
        }
        return targetView;
    }

    private void setItemViews(int position, Customer customer, View targetView) {
        TextView customerNumberView = (TextView) targetView.findViewById(R.id.customer_number);
        customerNumberView.setText("Customer " + position);
    }
}
