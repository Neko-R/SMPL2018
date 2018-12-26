package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

import java.util.ArrayList;
import java.util.Iterator;

public class IRExpMultiExp extends IRExp {

    ArrayList<IRExp> exps;

    public IRExpMultiExp(ArrayList<IRExp> exps) {
        this.exps = exps;
    }


    public ArrayList<IRExp> getExps() {
	return exps;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitExpMultiExp(this, arg);
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
