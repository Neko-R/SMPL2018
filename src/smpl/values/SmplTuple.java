package smpl.values;
import smpl.sys.SmplException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * SMPL representation for the empty list 
 **/
public class SmplTuple extends SmplObj {

    ArrayList<SmplObj> values;

    public SmplTuple() {
        super(Type.TUPLE);
    }

    @Override
    public SmplObj add(SmplObj val){
        values.add(val);
        return this;

    }

    public ArrayList<SmplObj> getValues(){
        return values;
    }

    @Override
    public String toString() {
        Iterator<SmplObj> iter = values.iterator();

        String result = "( ";
        while (iter.hasNext()) {
            result = result + iter.next().toString() + ",\n";
        }
        result = result + " )";
        return result;
    }
}
