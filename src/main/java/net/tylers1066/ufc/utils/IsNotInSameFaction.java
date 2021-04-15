package net.tylers1066.ufc.utils;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import org.bukkit.command.CommandSender;

import java.util.function.Predicate;

public class IsNotInSameFaction implements Predicate<CommandSender> {
    private final Faction baseFaction;

    public IsNotInSameFaction(CommandSender base) {
        baseFaction = MPlayer.get(base).getFaction();
    }

    @Override
    public boolean test(CommandSender other) {
        MPlayer otherMP = MPlayer.get(other);
        Faction otherFaction = otherMP.getFaction();
        Rel rel = baseFaction.getRelationTo(otherFaction);
        return !(rel.isAtLeast(Rel.FACTION));
    }
}