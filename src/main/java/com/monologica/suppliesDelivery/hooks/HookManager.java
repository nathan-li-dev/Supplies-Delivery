package com.monologica.suppliesDelivery.hooks;

import com.monologica.suppliesDelivery.SuppliesDeliveryPlugin;
import net.citizensnpcs.api.CitizensAPI;

public class HookManager {
    private boolean hasCitizens;

    public boolean hasCitizens() {
        return hasCitizens;
    }

    public HookManager(SuppliesDeliveryPlugin plugin) {
        hasCitizens = plugin.getServer().getPluginManager().isPluginEnabled("Citizens");
    }

    public void register() {
        if(hasCitizens) {
            CitizensAPI.getTraitFactory().registerTrait(net.citizensnpcs.api.trait.TraitInfo.create(SuppliesDeliveryOpenerTrait.class).withName("suppliesmenuopener"));
        }
    }

}
