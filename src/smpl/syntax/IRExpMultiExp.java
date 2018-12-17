package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

import java.util.ArrayList;
import java.util.Iterator;

public class IRExpMultiExp extends IRExp {

    ArrayList<IRExp> exps;

    public IRExpMultiExp() {
        exps = new ArrayList<>();
    }

    public IRExpMultiExp(IRExp s) { this(); exps.add(s);
    }

    public ArrayList<IRExp> getExps() {
	return exps;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitExpMultiExp(this, arg);
    }

    public IRExpMultiExp add(IRExp s){
        exps.add(s);
        return this;
    }

    @Override
    public String toString() {
        Iterator<IRExp> iter = exps.iterator();

        String result = "( ";
        while (iter.hasNext()) {
            result = result + iter.next().toString() + ",\n";
        }
        result = result + " )";
        return result;
    }
}
