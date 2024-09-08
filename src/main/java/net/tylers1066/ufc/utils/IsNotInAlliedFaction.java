package net.tylers1066.ufc.utils;

import org.bukkit.command.CommandSender;

import java.util.function.Predicate;

public class IsNotInAlliedFaction implements Predicate<CommandSender> {
    public IsNotInAlliedFaction(CommandSender base) {
    }

    @Override
    public boolean test(CommandSender other) {
        return true;
    }
}
