package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;
import java.lang.Math;
/** 
 * SMPL representation for integers (this implementation is only a suggestion) 
 **/
public class SmplInt extends SmplObj {

    
    private int value;
    
    /** 
     * Construct an instance of an SMPL integer.
     * @param v The value of the number.
     **/
    public SmplInt(int v) {
      super(Type.INT);
      value = v;
    }
    
    // You will need to figure out how to make this work well with floating point numbers

    /**
     *
     * @return the value of this number as an integer.  (It will be cast to
     * integer, if it is not natively an integer).
     */
    public int intValue() {
	return value;
    }
    
    public int value() {
        return value;
    }
    
    @Override
    public SmplObj add(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            return new SmplInt(value() + ((SmplInt) arg).value());
        } else 	if (arg instanceof SmplDouble) {
	    return new SmplDouble(value() + ((SmplDouble)arg).value());
        } else
            // go to parent class for default behaviour
	    return super.add(arg);
    }
    
    @Override
    public SmplObj sub(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            return new SmplInt(value() - ((SmplInt) arg).value());
        } else 	if (arg instanceof SmplDouble) {
	    return new SmplDouble(value() - ((SmplDouble)arg).value());
        } else
            // go to parent class for default behaviour
	    return super.add(arg);
    }

    // you will need to fill in the rest of the applicable operations


    @Override
    public SmplObj mul(SmplObj arg) throws SmplException {
        if (arg instanceof SmplInt){
            return new SmplInt(value() * ((SmplInt) arg).value() );
        }else if (arg instanceof SmplDouble){
            return new SmplDouble(value() * ((SmplDouble)arg).value());
        }else
        return super.mul(arg);
    }

    @Override
    public SmplObj div(SmplObj arg) throws SmplException {
        if (arg instanceof SmplInt){
            return new SmplInt(value() / ((SmplInt) arg).value() );
        }else if (arg instanceof SmplDouble){
            return new SmplDouble(value() / ((SmplDouble)arg).value());
        }else
        return super.div(arg);
    }

    @Override
    public SmplObj mod(SmplObj arg) throws SmplException {
        if (arg instanceof SmplInt){
            return new SmplInt(value() % ((SmplInt) arg).value() );
        }else if (arg instanceof SmplDouble){
            return new SmplDouble(value() % ((SmplDouble)arg).value());
        }else
        return super.mod(arg);
    }

    @Override
    public SmplObj expt(SmplObj arg) throws SmplException {
        if (arg instanceof SmplInt){
            return new SmplInt( (int) Math.pow((double) value(), (double) ((SmplInt) arg).value()));
        }else if (arg instanceof SmplDouble){
            return new SmplDouble(Math.pow((double) value(), ((SmplDouble) arg).value()));
        }else
        return super.expt(arg);
    }

    @Override
    public SmplObj neg() throws SmplException {
        return new SmplInt(-value());
    }

    @Override
    public SmplObj eq(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() == ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() == ((SmplDouble)arg).value());
        }else
        return super.eq(arg);
    }

    public SmplObj neq(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() != ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() != ((SmplDouble)arg).value());
        }else
            return super.neq(arg);
    }

   /* @Override
    public boolean equals(SmplObj obj) {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(((SmplInt)arg).value() == ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(((SmplInt)arg).value() == ((SmplDouble)arg).value());
        }else
        return super.equals(obj);
    }*/

    @Override
    public SmplObj lt(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() < ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() < ((SmplDouble)arg).value());
        }else
        return super.lt(arg);
    }

    @Override
    public SmplObj le(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() <= ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() <= ((SmplDouble)arg).value());
        }else
        return super.le(arg);
    }

    @Override
    public SmplObj gt(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() > ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() > ((SmplDouble)arg).value());
        }else
        return super.gt(arg);
    }

    @Override
    public SmplObj ge(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() >= ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() >= ((SmplDouble)arg).value());
        }else
        return super.ge(arg);
    }

    @Override
    public SmplObj orBits(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return new SmplInt(value() | ((SmplInt)arg).value());
        }else
        return super.orBits(arg);
    }

    @Override
    public SmplObj andBits(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return new SmplInt(value() & ((SmplInt)arg).value());
        }else
        return super.andBits(arg);
    }

    @Override
    public SmplObj notBits() {
        return new SmplInt(~ value());
    }

    @Override
    public String toString() {
        return String.format("%s", value());
    }

    @Override
    public boolean equals(SmplObj arg) {
        if (arg instanceof SmplInt){
            return value() == ((SmplInt) arg).value();
        }else
            return false;
    }
}
