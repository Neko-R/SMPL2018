package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

import java.util.ArrayList;
import java.util.Iterator;

public class BindingList {

    ArrayList<Binding> bindingLst;		// sequence of bindings

    public BindingList() {
	bindingLst = new ArrayList<>();
    }

    public BindingList(Binding b) {
	this();
	bindingLst.add(b);
    }

    public ArrayList<Binding> getSeq() {
	return bindingLst;
    }

    public BindingList add(Binding b) {
	bindingLst.add(b);
	return this;
    }

    @Override
    public String toString() {
        Iterator<Binding> iter = bindingLst.iterator();

        String result = "(";
        while (iter.hasNext()) {
            result = result + iter.next().toString();
            if(iter.hasNext()){
                result += ", ";
            }
        }
        result += ")";
        return result;
        }

}

