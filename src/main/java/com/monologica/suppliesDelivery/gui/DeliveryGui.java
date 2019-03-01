package com.monologica.suppliesDelivery.gui;

import com.monologica.suppliesDelivery.SuppliesDeliveryPlugin;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;

public class DeliveryGui {

    private SmartInventory inv;

    public DeliveryGui(SuppliesDeliveryPlugin plugin) {
        this.inv = SmartInventory.builder()
                .provider(new DeliveryGuiProvider(plugin))
                .size(3,9)
                .title(plugin.getGuiConfig().name())
                .build();
    }

    public void open(Player player) {
        inv.open(player);
    }

}
