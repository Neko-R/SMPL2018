package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

import java.util.ArrayList;

public class IRExpCase extends IRExp {
    ArrayList<Clause> clauses;
    IRExp elseClause;

    public IRExpCase(ArrayList<Clause> c, IRExp els) {
        clauses = c;
        elseClause = els;
    }

    public IRExpCase(ArrayList<Clause> c) {
        clauses = c;
    }

    public ArrayList<Clause> getClauses() {
        return clauses;
    }

    public IRExp getElseClause() {
        return elseClause;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	      return v.visitIRExpCase(this, arg);
    }

    public String toString() {
	    String result = "case{ ";
        for (Clause c: clauses) {
            result += c + ",";
        }

        if(elseClause == null)
            return result.substring(0, result.length()-1) + " }";
        else
            return result + "else : "+ elseClause + " }";
    }

}
