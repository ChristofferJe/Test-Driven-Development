package hotstone.standard;

import hotstone.framework.*;

import java.util.ArrayList;


public class StandardField implements Field {

    private ArrayList<Card> field;

    public StandardField(){
        field = new ArrayList<Card>();
    }
    @Override
    public int getSize() {
        return 0;
    }

    public void add(Card card) {
        field.add(0,card);
    }

    public Iterable<? extends Card> getField() {
        return field;
    }
}
