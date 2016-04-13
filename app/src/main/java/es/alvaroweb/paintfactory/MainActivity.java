package es.alvaroweb.paintfactory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import es.alvaroweb.paintfactory.adapters.CasesAdapter;
import es.alvaroweb.paintfactory.comunication.CaseSet;

public class MainActivity extends AppCompatActivity {
    CasesAdapter casesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        casesAdapter = new CasesAdapter(this);
        ListView casesListView = (ListView) findViewById(R.id.cases_grid_view);
        casesListView.setAdapter(casesAdapter);

        casesListView.setOnItemClickListener(onItemClick());
        casesListView.setOnItemLongClickListener(onItemLongClick());

    }

    private AdapterView.OnItemLongClickListener onItemLongClick() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "hei", Toast.LENGTH_LONG).show();
                return false;
            }
        };
    }
    /** this call the next Activity*/
    private AdapterView.OnItemClickListener onItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "pos:"+position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
                intent.putExtra(Arguments.CASE_ARG, position); //TODO
                startActivity(intent);
            }
        };
    }

    /** this only adds a empty case*/
    public void clickAddButton(View view){
        CaseSet.getInstance().addCase();
        casesAdapter.notifyDataSetChanged();
    }

    public void clickCheckButton(View view){
        Snackbar.make(view, "Are you sure?", Snackbar.LENGTH_LONG)
                .setAction("do it!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

}
