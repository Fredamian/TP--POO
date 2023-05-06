package foxrabbitsv2;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Hunter {

    private int shots;
    private final double hitting_probability;

    private boolean activo;
    private Campo field;
    // The animal's position in the field.
    private Localizacao location;

    private static final Random rand = Randomizer.getRandom();

    public Hunter(int shots, double hitting_probability, Campo field, Localizacao local) {
        this.shots = shots;
        this.hitting_probability = hitting_probability;
        this.activo = true;
        this.field = field;
        this.location = local;
    }

    public int getShots() {
        return shots;
    }

    public double getHitting_probability() {
        return hitting_probability;
    }

    public boolean isActivo() {
        return activo;
    }

    public Campo getField() {
        return field;
    }

    public Localizacao getLocation() {
        return location;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setField(Campo field) {
        this.field = field;
    }

    public void setLocation(Localizacao newLocation) {

        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    public void act() {
        //incrementAge();
        //incrementHunger();
        if(isActivo()) {

            // Move towards a source of food if found.
            Localizacao newLocation = findPrey();
            if(newLocation == null) {
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setInactivo();
            }
        }
    }

    private Localizacao findPrey() {
        Campo field = getField();
        Localizacao location = getLocation();
        List<Localizacao> adjacent = field.adjacentLocations(location);
        Iterator<Localizacao> it = adjacent.iterator();
        while(it.hasNext()) {
            Localizacao where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Coelho) {
                Coelho rabbit = (Coelho) animal;
                if  (rabbit.isAlive()   ) {
                    rabbit.setDead();
                    return where;
                }
            } else if (animal instanceof Raposa) {
                Raposa fox = (Raposa) animal;
                if  (fox.isAlive()   ) {
                    fox.setDead();
                    return where;
                }
            }
        }
        return null;
    }

    private void setInactivo() {
        {
            activo = false;
            if(location != null) {
                field.clear(location);
                location = null;
                field = null;
            }
        }
    }
}
