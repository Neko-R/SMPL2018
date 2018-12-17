package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

import java.util.ArrayList;
import java.util.Iterator;

public class ParamLst{

    ArrayList<Parameter> paramLst;		// paramLst of ids


    public ParamLst() {
	paramLst = new ArrayList<>();
    }

    public ParamLst(String mod, String p) {
	this();
	paramLst.add(new Parameter(mod, p));
    }

    public ParamLst add(String mod, String p){
        paramLst.add( new Parameter(mod,p));
        return this;
    }
    public ArrayList<Parameter> getparamLst() {
	return paramLst;
    }

    @Override
    public String toString() {
        Iterator<Parameter> iter = paramLst.iterator();

	String result = "";
	while (iter.hasNext()) {
	    result = result + iter.next().toString();
	    if(iter.hasNext()){
	        result += ", ";
        }
	}

	return result;
    }

}

