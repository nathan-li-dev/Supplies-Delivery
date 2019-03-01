package com.monologica.suppliesDelivery.supplyDelivery;

import com.monologica.suppliesDelivery.SuppliesDeliveryPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ResetDemandTask extends BukkitRunnable {

    private final SuppliesDeliveryPlugin plugin;

    public ResetDemandTask(SuppliesDeliveryPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.getSupplyManager().reset();
    }

}
