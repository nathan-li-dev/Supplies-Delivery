package com.monologica.suppliesDelivery.supplyDelivery;

import com.monologica.suppliesDelivery.SuppliesDeliveryPlugin;
import com.monologica.suppliesDelivery.utils.Items;
import org.bukkit.entity.Player;

public class Transaction {
    private Player seller;
    private SupplyItem supplyItem;
    private int amountToSell;

    public Transaction(Player seller, SupplyItem item, int amount) {
        this.seller = seller;
        this.supplyItem = item;
        this.amountToSell = amount;
    }

    public CompletedTransaction execute() {
        int amountSold = Items.takeFrom(seller, supplyItem, amountToSell);
        double revenue = supplyItem.marketValue() * amountSold;

        supplyItem.buy(amountSold);
        SuppliesDeliveryPlugin.economy.depositPlayer(seller, revenue);

        return new CompletedTransaction(amountSold, revenue);
    }
}
