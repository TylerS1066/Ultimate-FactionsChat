package net.tylers1066.ufc.listener;

import br.net.fabiozumbi12.UltimateChat.Bukkit.API.PostFormatChatMessageEvent;
import net.tylers1066.ufc.utils.IsNotInAlliedFaction;
import net.tylers1066.ufc.utils.IsNotInSameFaction;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(PostFormatChatMessageEvent e) {
        if(e.getChannel().getName().equals("Faction")) {
            handleFactionChat(e);
        }
        else if(e.getChannel().getName().equals("Ally")) {
            handleAllyChat(e);
        }
    }

    private void handleFactionChat(PostFormatChatMessageEvent e) {
        HashMap<CommandSender, ?> messages = e.getMessages();
        messages.keySet().removeIf(new IsNotInSameFaction(e.getSender()));
    }

    private void handleAllyChat(PostFormatChatMessageEvent e) {
        HashMap<CommandSender, ?> messages = e.getMessages();
        messages.keySet().removeIf(new IsNotInAlliedFaction(e.getSender()));
    }
}