package es.alvaroweb.paintfactory.comunication;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import paintfactory.prototype.Case;
import paintfactory.prototype.Customer;
import paintfactory.prototype.Factory;

/**
 * Created by Alvaro on 13/04/2016.
 */
public class CaseSet {
    private List<Case> cases;
    private static final CaseSet INSTANCE = new CaseSet();

    private CaseSet() {
        cases = new ArrayList<>();
    }
    
    public static CaseSet getInstance(){
        return INSTANCE;
    }

    public List<Case> getCases() {
        return cases;
    }

    public Case getCase(int i){return cases.get(i);}

    public void addCase(int numberOfPaints){
        cases.add(new Case(numberOfPaints, new ArrayList<Customer>()));
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public void removeCase(int position) {
        cases.remove(position);
    }
    public String generateResults() throws IOException {
        Factory factory = new Factory(this.cases);
        factory.makePaints();
        OutputStream os = new ByteArrayOutputStream();
        factory.getDataResults().writeCasesToOutputStream(os);
        return os.toString();
    }
}
