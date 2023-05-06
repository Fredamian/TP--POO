package foxrabbitsv2;

import foxrabbitsv2.*;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Leao extends Animal {

    private static final int BREEDING_AGE = 15;

    private static final int MAX_AGE = 300;

    private static final double BREEDING_PROBABILITY = 1;

    private static final int MAX_LITTER_SIZE = 3;

    // value that a rabbit can give to a lion
    private static final int RABBIT_FOOD_VALUE = 5;

    // valor que uma raposa pode atribuir ao Leão quando este estiver com fome
    private static final int FOX_FOOD_VALUE = 4;
    private static final Random rand = Randomizer.getRandom();
    private int age;
    private int foodLevel;


    // o construtor
    public Leao(boolean randomAge, Campo field, Localizacao location)
    {
        super(field,location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);   // foodLevel é inicializado com um numero aleatorio
        }
        else {
            age = 0; // leave age at 0
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE); // foodLevel é inicializado com um numero aleatorio
        }
    }

    // fazendo o Override de act, o leão ou pode procurar comida ou entao se encontrar uma raposa ele o mata
    @Override
    public void act(List<Animal> newLeao){
        incrementAge();
        incrementHunger();
        if(isAlive()) {  // se o leao ainda estiver vivo
            giveBirth(newLeao);
            Localizacao newLocation = findFood();
            if(newLocation == null) {
                if(foodLevel >= 3)   // se o nivel de fome for maior do que 3
                newLocation = getField().freeAdjacentLocation(getLocation()); // entao ele procura outro lugar
                else {    // senao
                    newLocation = eatFox();  //ele procura se tem alguma raposa por perto
                    if(newLocation == null)             //se nao estiver nenhuma raposa por perto
                        newLocation = getField().freeAdjacentLocation(getLocation());  // ele muda de posicao
                }
            }
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                setDead();
            }
        }
    }
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    /**
     * Aumenta a fome do Leao
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }

    private Localizacao findFood()
    {
        List<Localizacao> adjacent = getField().adjacentLocations(getLocation());
        Iterator<Localizacao> it = adjacent.iterator();
        while(it.hasNext()) {
            Localizacao where = it.next();
            Object animal = getField().getObjectAt(where);
            if(animal instanceof Coelho) {
                Coelho rabbit = (Coelho) animal;
                if(rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return where;
                }
            }

        }
        return null;
    }

    /*private Localizacao killFox(){
        List<Localizacao> adjacent = getField().adjacentLocations(getLocation());
        Iterator<Localizacao> it = adjacent.iterator();
        while(it.hasNext()) {
            Localizacao where = it.next();
            Object animal = getField().getObjectAt(where);
            if(animal instanceof Raposa) {
                Raposa fox = (Raposa) animal;
                if(fox.isAlive()) {
                    fox.setDead();
                    return where;
                }
            }

        }
        return null;
    }*/

    private Localizacao eatFox(){  // se o leao estiver com muita fome ele pode comer a raposa

        List<Localizacao> adjacent = getField().adjacentLocations(getLocation());
        Iterator<Localizacao> it = adjacent.iterator();
        while(it.hasNext()) {
            Localizacao where = it.next();
            Object animal = getField().getObjectAt(where);
            if(animal instanceof Raposa) {
                Raposa raposa_nutr = (Raposa) animal; // Raposa Nutritiva
                if(raposa_nutr.isAlive()) {
                    raposa_nutr.setDead();
                    foodLevel = FOX_FOOD_VALUE;
                    return where;
                }
            }

        }
        return null;
    }

    private void giveBirth(List<Animal> newLion)
    {
        List<Localizacao> free = getField().getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Localizacao loc = free.remove(0);
            Leao young = new Leao(false,super.getField(),loc);
            newLion.add(young);
        }
    }

    private int breed() {
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
}