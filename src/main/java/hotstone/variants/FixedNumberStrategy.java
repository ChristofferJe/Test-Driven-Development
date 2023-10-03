package hotstone.variants;

import hotstone.framework.PickNumberStrategy;

public class FixedNumberStrategy implements PickNumberStrategy {
    private int number;

    @Override
    public int getNumber(int fieldSize) {
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }
}
