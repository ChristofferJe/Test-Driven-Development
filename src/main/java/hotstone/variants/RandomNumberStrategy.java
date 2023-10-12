package hotstone.variants;

import hotstone.framework.PickNumberStrategy;

import java.util.Random;

public class RandomNumberStrategy implements PickNumberStrategy {

    @Override
    public int getNumber(int fieldSize) {
        Random rand = new Random();
        return rand.nextInt(fieldSize);
    }

    @Override
    public void setNumber(int number) {}


}
