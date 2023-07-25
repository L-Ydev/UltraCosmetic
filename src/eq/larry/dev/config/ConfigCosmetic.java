package eq.larry.dev.config;

import dev.lone.itemsadder.api.CustomStack;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigCosmetic {
    private final FileConfiguration fileConfiguration;

    public ConfigCosmetic(Plugin plugin) {
        this.fileConfiguration = plugin.getConfig();
    }

    public boolean isHatCosmetic(CustomStack customStack) {
        List<?> list = this.fileConfiguration.getList("hats");
        if (customStack == null) {
            return false;
        } else {
            return list == null ? false : list.contains(customStack.getNamespacedID());
        }
    }

    public boolean isChestCosmetic(CustomStack customStack) {
        List<?> list = this.fileConfiguration.getList("chest");
        if (customStack == null) {
            return false;
        } else {
            return list == null ? false : list.contains(customStack.getNamespacedID());
        }
    }
}