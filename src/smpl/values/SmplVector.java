package smpl.values;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * SMPL representation for the empty list 
 **/
public class SmplVector extends SmplObj {
    ArrayList<SmplObj> array;

    public SmplVector() {
        super(Type.VECTOR);
    }

    public SmplVector(ArrayList<SmplObj> array){
        this();
        this.array = array;
    }

    @Override
    public boolean isList() {
        if(this.getType() == Type.VECTOR){
            return true;
        }
        return false;
    }

    public ArrayList<SmplObj> getArray() {
        return this.array;
    }

    @Override
    public String toString() {
        Iterator<SmplObj> iter = array.iterator();

        String result = "[: ";
        while (iter.hasNext()) {
            result = result + iter.next().toString();
            if(iter.hasNext()){
                result += ", ";
            }
        }

        return result + " :]";
    }
}
