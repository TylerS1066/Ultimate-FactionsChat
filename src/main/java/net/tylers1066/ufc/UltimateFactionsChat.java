package net.tylers1066.ufc;

import br.net.fabiozumbi12.UltimateChat.Bukkit.API.PostFormatChatMessageEvent;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.perms.Relation;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public final class UltimateFactionsChat extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(@NotNull PostFormatChatMessageEvent e) {
        Relation minimum = switch (e.getChannel().getName().toLowerCase()) {
            case "faction" -> Relation.MEMBER;
            case "ally" -> Relation.ALLY;
            default -> null;
        };
        if (minimum == null)
            return;

        e.getMessages().keySet().removeIf(new IsNotInRelatedFaction(e.getSender(), minimum));
    }

    public static class IsNotInRelatedFaction implements Predicate<CommandSender> {
        @Nullable
        private final Faction baseFaction;
        private final Relation minimumRelation;

        public IsNotInRelatedFaction(CommandSender base, Relation minimumRelation) {
            baseFaction = getFaction(base);
            this.minimumRelation = minimumRelation;
        }

        @Override
        public boolean test(CommandSender other) {
            if (baseFaction == null || !(other instanceof Player otherPlayer))
                return false;
            return !(baseFaction.getRelationTo(getFaction(otherPlayer)).isAtLeast(minimumRelation));
        }

        @Nullable
        private static Faction getFaction(CommandSender sender) {
            if (!(sender instanceof Player player))
                return null;
            return FPlayers.getInstance().getByPlayer(player).getFaction();
        }
    }
}
