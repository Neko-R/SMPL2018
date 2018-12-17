package smpl.syntax;

public class Parameter {

    String id;
    String modifier;

    public Parameter(String modi, String p) {
	id = p;
	modifier = modi;
    }

    public String getId() {
	return id;
    }

    public String getModifier() {
	return modifier;
    }

    @Override
    public String toString() {
        if(modifier == "lazy"){
            return modifier + " " + id;
        }else{
            return  id;
        }

    }
}
