package com.monologica.suppliesDelivery.supplyDelivery;

import com.monologica.suppliesDelivery.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SupplyManager {
    private List<SupplyItem> supplies;

    public List<SupplyItem> getSupplies() {
        return supplies;
    }

    public SupplyManager(YamlConfiguration yml) {
        supplies = new ArrayList<>();

        for(String id: yml.getConfigurationSection("").getKeys(false)) {
            Material material = Material.valueOf(yml.getString(id + ".material"));
            String name = yml.getString(id + ".name");
            List<String> lore = yml.getStringList(id + ".lore");
            ItemStack item = Items.build(name, lore, material);

            double baseValue =  yml.getDouble(id + ".baseValue");
            int demand = yml.getInt(id + ".demand");
            float maxBonus = (float)yml.getDouble(id + ".maxBonus");
            float minMultiplier = (float)yml.getDouble(id + ".minMultiplier");

            supplies.add(new SupplyItem(item, baseValue, demand, minMultiplier, maxBonus));
        }
    }

    public void buy(SupplyItem supplyItem, int amount) {
        supplyItem.buy(amount);
    }

    public void reset() {
        Bukkit.broadcastMessage("Resetting demand...");

        for(SupplyItem item: supplies) {
            item.reset();
        }
    }
}
