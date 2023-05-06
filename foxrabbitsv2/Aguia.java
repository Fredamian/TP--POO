package foxrabbitsv2;

import foxrabbitsv2.Animal;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Aguia extends Animal {

    private static final int BREEDING_AGE = 5;
    private static final int MAX_AGE = 35;
    private static final int MAX_ALTITUDE = 7000;

    private static final double BREEDING_PROBABILITY = 0.5;

    private static final int MAX_LITTER_SIZE = 3;

    private static final int RABBIT_FOOD_VALUE = 5;
    private static final int FOX_FOOD_VALUE = 35;


    private static final Random rand = Randomizer.getRandom();

    private int age;
    private int altitude;

    private int foodLevel;
    // tempo de vida de 30 a 35 anos
    // As águias começam a tomar os primeiros passos para a vida sozinha
    // por volta das 12 semanas de vida, que é quando elas começam a
    // aprender a voar.

    //capaz de ver suas presas a quase 2 km de distância,

    /**
     * Create a new animal at location in field.
     *
     * @param field The field currently occupied.
     * @param local The location within the field.
     */
    public Aguia(boolean randomAlt, boolean randomAge, Campo field, Localizacao local) {
        super(field,local);
        age = randomAge ? 0 : rand.nextInt(MAX_AGE);
        altitude = randomAlt ? 0 : rand.nextInt(MAX_ALTITUDE);
        foodLevel = rand.nextInt(FOX_FOOD_VALUE);
    }

    @Override
    public void act(List<Animal> newAnimals) {
        incrementAge();
        incrementHunger();
        fly();
        if(isAlive()) {
            giveBirth(newAnimals);
            // Move towards a source of food if found.
            Localizacao newLocation = findFood();
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
                setDead();
            }
        }

    }

    private void fly() {
        altitude =  rand.nextInt(MAX_ALTITUDE);
    }

    private Localizacao findFood() {
        Campo field = getField();
        Localizacao location = getLocation();
        List<Localizacao> adjacent = field.adjacentLocations(location);
        Iterator<Localizacao> it = adjacent.iterator();
        while(it.hasNext()) {
            Localizacao where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Coelho) {
                Coelho rabbit = (Coelho) animal;
                if( (rabbit.isAlive() && (altitude < 1))  ) {
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return where;
                }
            } else if(animal instanceof Raposa) {
                Raposa fox = (Raposa) animal;
                if( (fox.isAlive() && (altitude < 2))  ) {
                    fox.setDead();
                    foodLevel = FOX_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }

    private void giveBirth(List<Animal> newAnimals) {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Campo field = getField();
        List<Localizacao> free = field.getFreeAdjacentLocations(getLocation());

        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Localizacao loc = free.remove(0);
            Aguia cria = new Aguia(false, false,field, loc);
            newAnimals.add(cria);
        }
    }
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }

    private void incrementHunger() {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }

    private void incrementAge() {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
}
