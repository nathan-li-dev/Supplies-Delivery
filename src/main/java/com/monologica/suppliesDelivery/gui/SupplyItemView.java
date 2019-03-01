package com.monologica.suppliesDelivery.gui;

import com.monologica.suppliesDelivery.supplyDelivery.SupplyItem;
import com.monologica.suppliesDelivery.supplyDelivery.SupplyManager;

import java.util.ArrayDeque;
import java.util.List;

public class SupplyItemView {

    private static final int VIEW_SIZE = 7;

    private ArrayDeque<SupplyItem> view;
    private List<SupplyItem> supplies;

    private int from;
    private int to;

    public SupplyItemView(SupplyManager manager) {
        this.supplies = manager.getSupplies();
        this.view = new ArrayDeque<>();

        from = 0;
        for(int i = 0; i < VIEW_SIZE; i++) {
            if(i >= supplies.size())
                break;
            view.add(supplies.get(i));
            to = i;
        }
    }

    public boolean next() {
        if(to + 1 >= supplies.size())
            return false;

        view.removeFirst();
        view.addLast(supplies.get(++to));
        from++;
        return true;
    }

    public boolean back() {
        if(this.from == 0)
            return false;

        view.removeLast();
        view.addFirst(supplies.get(--from));
        to--;
        return true;
    }

    public ArrayDeque<SupplyItem> get() {
        return view;
    }

}
