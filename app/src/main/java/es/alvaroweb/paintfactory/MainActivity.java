package es.alvaroweb.paintfactory;

import android.app.DialogFragment;
import android.content.DialogInterface;
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
    CaseSet caseSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        casesAdapter = new CasesAdapter(this);
        caseSet = CaseSet.getInstance();
        ListView casesListView = (ListView) findViewById(R.id.cases_grid_view);
        casesListView.setAdapter(casesAdapter);

        casesListView.setOnItemClickListener(onItemClick());
        casesListView.setOnItemLongClickListener(onItemLongClick());

    }
    /** Long click on item will delete it*/
    private AdapterView.OnItemLongClickListener onItemLongClick() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                Snackbar.make(view, "Do you want to delete it?", Snackbar.LENGTH_LONG)
                        .setAction("do it!", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                caseSet.removeCase(position);
                                casesAdapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        };
    }
    /** this call the next Activity*/
    private AdapterView.OnItemClickListener onItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
                intent.putExtra(Arguments.CASE_ARG, position); //TODO
                startActivity(intent);
            }
        };
    }

    /** this only adds a empty case*/
    public void clickAddButton(View view){
        CaseDialog caseDialog = new CaseDialog(this);
        caseDialog.show();
        caseDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                casesAdapter.notifyDataSetChanged();
            }
        });
    }

    public void clickCheckButton(View view){
        Snackbar.make(view, "Are you ready to see the results?", Snackbar.LENGTH_LONG)
                .setAction("do it!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        startActivity(intent);
                    }
                }).show();
    }

}
