package smpl.values;

import smpl.sys.SmplException;

/**
 * SMPL representation for the empty list 
 **/
public class SmplNil extends SmplObj {

    String value = "#e";

    public SmplNil() {
        super(Type.LIST);
    }
  
    @Override
    public boolean isFalse() {
        return true;
    }
    
    @Override
    public String toString() {
        return "#e";
    }

    public String getValue() {
        return value;
    }

    public boolean isNil() {
        return ((this.getType() == Type.LIST) && (value == "#e"));
    }

    @Override
    public boolean equals(SmplObj arg) {
        if (arg instanceof SmplNil){
            return value == ((SmplNil) arg).getValue();
        }else
            return false;
    }

    @Override
    public SmplObj eq(SmplObj arg) throws SmplException {
        if (arg instanceof SmplNil){
            return SmplBool.getConst(value == ((SmplNil) arg).getValue());
        }else
            return SmplBool.getConst(false);
    }
}
