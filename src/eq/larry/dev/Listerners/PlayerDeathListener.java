package eq.larry.dev.Listerners;

import dev.lone.itemsadder.api.CustomStack;
import eq.larry.dev.config.ConfigCosmetic;

import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerDeathListener implements Listener {
    private final ConfigCosmetic configCosmetic;

    public PlayerDeathListener(Plugin plugin) {
        this.configCosmetic = new ConfigCosmetic(plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        List<ItemStack> list = event.getDrops();
        ItemStack head = event.getPlayer().getInventory().getItem(EquipmentSlot.HEAD);
        ItemStack chest = event.getPlayer().getInventory().getItem(EquipmentSlot.CHEST);
        CustomStack cosmeticHead = CustomStack.byItemStack(head);
        CustomStack cosmeticChest = CustomStack.byItemStack(chest);
        if (cosmeticHead != null && this.configCosmetic.isHatCosmetic(cosmeticHead)) {
            list.remove(head);
        }

        if (cosmeticChest != null && this.configCosmetic.isChestCosmetic(cosmeticChest)) {
            list.remove(chest);
        }

    }
}