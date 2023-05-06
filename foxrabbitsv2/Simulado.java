package foxrabbitsv2;

import java.util.List;

public abstract class Simulado {

    public Simulado(Campo field, Localizacao location){
        this.field = field;
        this.location = location;
    }

    private Campo field;
    private Localizacao location;

    public Localizacao getLocation(){
        return location;
    }

    protected void setLocation(Localizacao location){

        if(getLocation() != null) {
            getField().clear(getLocation());
        }
        this.location = location;
        field.place(this, location);
    }

    protected Campo getField(){

        return field;
    }
    public void setField(Campo field){
        this.field = field;
    }
    abstract public void act(List<Animal> newAnimal);






}
