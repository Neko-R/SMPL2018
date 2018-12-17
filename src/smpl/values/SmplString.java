package smpl.values;


public class SmplString extends SmplObj {

    String value;

    public SmplString(String c) {
        super(Type.STRING);
        value = c;
    }
  
    @Override
    public boolean isFalse() {
        return true;
    }
    
    @Override
    public String toString() {
        return ""+value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(SmplObj arg) {
        if (arg instanceof SmplString){
            return value == ((SmplString) arg).getValue();
        }else
            return false;
    }
}
