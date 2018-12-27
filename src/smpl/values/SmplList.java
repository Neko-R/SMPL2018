package smpl.values;

import smpl.syntax.IRExp;
import smpl.sys.SmplException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * SMPL representation for the empty list 
 **/
public class SmplList extends SmplObj {
    ArrayList<SmplObj> array;
    SmplPair list;

    public SmplList() {
        super(Type.LIST);
    }

    public SmplList(ArrayList<SmplObj> args){
        this();
        if(args.size() != 0) {
            this.list = new SmplPair(args.get(args.size() - 1), SmplObj.NIL);
            for (int i = args.size() - 2; i >= 0; i--) {
                this.list = new SmplPair(args.get(i), this.list);
            }
            this.array = args;
        }
    }

    public SmplPair getList() {
        return list;
    }

    @Override
    public boolean isList() {
        if(this.getType() == Type.LIST){
            return true;
        }
        return super.isList();
    }

    public ArrayList<SmplObj> getArray() {
        return this.array;
    }

    @Override
    public String toString() {
        SmplPair next = list;
        String result = "[";
        if(list != null) {
            while (next.isPair()) {
                result = result + next.first().toString();
                if (next.second.isPair()) {
                    result += ", ";
                    next = (SmplPair) next.second();
                } else
                    break;
            }
        }
        return result + "]";
    }

    @Override
    public SmplObj add(SmplObj arg) throws SmplException {
        if (arg instanceof SmplList) {
            ArrayList<SmplObj>  nl = getArray();
            for (SmplObj obj: ((SmplList)arg).getArray()) {
                nl.add(obj);
            }
            return new SmplList(nl);
        } else
            return super.add(arg);
    }
}
