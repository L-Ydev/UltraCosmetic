package eq.larry.dev.Listerners;


import dev.lone.itemsadder.api.CustomStack;
import eq.larry.dev.config.ConfigCosmetic;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class InventoryClickListener implements Listener {
    private static final int SLOT_HAT = 9;
    private static final int SLOT_TORSO = 10;
    private final ConfigCosmetic configCosmetic;

    public InventoryClickListener(Plugin plugin) {
        this.configCosmetic = new ConfigCosmetic(plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        int rawSlot = event.getRawSlot();
        ItemStack currentItem = event.getCurrentItem();
        CustomStack customStack = CustomStack.byItemStack(event.getCurrentItem());
        if (currentItem != null) {
            if (rawSlot == 5 || rawSlot == 6) {
                if (this.configCosmetic.isHatCosmetic(customStack)) {
                    event.setCancelled(true);
                } else if (this.configCosmetic.isChestCosmetic(customStack)) {
                    event.setCancelled(true);
                }
            }

            if (customStack == null) {
                if (rawSlot == 9) {
                    this.equipCosmetic(event.getCursor(), player, EquipmentSlot.HEAD);
                    this.equipCosmetic(player.getInventory().getItem(10), player, EquipmentSlot.CHEST);
                } else if (rawSlot == 10) {
                    this.equipCosmetic(player.getInventory().getItem(9), player, EquipmentSlot.HEAD);
                    this.equipCosmetic(event.getCursor(), player, EquipmentSlot.CHEST);
                }
            } else if (rawSlot == 9) {
                this.removeCosmetic(player, EquipmentSlot.HEAD);
            } else if (rawSlot == 10) {
                this.removeCosmetic(player, EquipmentSlot.CHEST);
            }

        }
    }

    private void equipCosmetic(ItemStack item, Player player, EquipmentSlot equipmentSlot) {
        if (item != null) {
            CustomStack stack = CustomStack.byItemStack(item);
            if (stack != null) {
                if (equipmentSlot == EquipmentSlot.HEAD && !this.configCosmetic.isHatCosmetic(stack)) {
                    return;
                }

                if (equipmentSlot == EquipmentSlot.CHEST && !this.configCosmetic.isChestCosmetic(stack)) {
                    return;
                }

                player.getInventory().setItem(equipmentSlot, item);
            }
        }

    }

    private void removeCosmetic(Player player, EquipmentSlot equipmentSlot) {
        ItemStack itemStack = player.getInventory().getItem(equipmentSlot);
        CustomStack stack = CustomStack.byItemStack(itemStack);
        if (stack != null) {
            player.getInventory().setItem(equipmentSlot, (ItemStack)null);
        }

    }
}