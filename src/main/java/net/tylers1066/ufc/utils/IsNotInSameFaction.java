package net.tylers1066.ufc.utils;

import org.bukkit.command.CommandSender;

import java.util.function.Predicate;

public class IsNotInSameFaction implements Predicate<CommandSender> {
    public IsNotInSameFaction(CommandSender base) {
    }

    @Override
    public boolean test(CommandSender other) {
        return true;
    }
}
