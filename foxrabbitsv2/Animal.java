package foxrabbitsv2;

import java.util.List;

public abstract class Animal extends Simulado{
    private boolean alive;



    public Animal(Campo field, Localizacao local) {
        super(field,local);
        alive = true;
    }



    protected boolean isAlive(){
        return alive;
    }

    protected void setDead() {
        alive = false;
        if (getLocation() != null) {
            getField().clear(getLocation());
            setLocation(null);
            setField(null);
        }
    }

    abstract public void act(List<Animal> newAnimal);

}
