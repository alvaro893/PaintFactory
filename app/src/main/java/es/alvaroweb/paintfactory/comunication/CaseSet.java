package es.alvaroweb.paintfactory.comunication;

import java.util.ArrayList;
import java.util.List;
import paintfactory.prototype.Case;
import paintfactory.prototype.Customer;

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

    public void addCase(){
        cases.add(new Case(1, new ArrayList<Customer>()));
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }
}
