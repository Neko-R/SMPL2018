package smpl.values;

import smpl.sys.SmplException;

/**
 * SMPL representation for the empty list 
 **/
public class SmplPair extends SmplObj {

    SmplObj first;
    SmplObj second;

    public SmplPair() {
        super(Type.PAIR);
    }

    public SmplPair(SmplObj f, SmplObj s){
        this();
        first = f;
        second = s;
    }

    @Override
    public boolean isPair() {
        if(this.getType() == Type.PAIR){
            return true;
        }
        return super.isPair();
    }

    @Override
    public SmplObj first() {
        return first;
    }

    @Override
    public SmplObj second() {
        return second;
    }

    @Override
    public String toString() {
        return "<" + first + ", " + second + ">";
    }
}
