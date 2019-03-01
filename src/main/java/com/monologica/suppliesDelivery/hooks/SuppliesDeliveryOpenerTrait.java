package com.monologica.suppliesDelivery.hooks;

import com.monologica.suppliesDelivery.SuppliesDeliveryPlugin;
import com.monologica.suppliesDelivery.gui.DeliveryGui;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;

@TraitName("suppliesmenuopener")
public class SuppliesDeliveryOpenerTrait extends Trait {

    private final SuppliesDeliveryPlugin plugin;

    public SuppliesDeliveryOpenerTrait() {
        super("suppliesmenuopener");
        plugin = JavaPlugin.getPlugin(SuppliesDeliveryPlugin.class);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void click(net.citizensnpcs.api.event.NPCRightClickEvent e) {
        if (e.getNPC() == this.getNPC()) {
            DeliveryGui gui = new DeliveryGui(plugin);
            gui.open(e.getClicker());
        }
    }
}
