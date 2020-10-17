package PingPackage;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class MasterMessageRespond {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        Message messageMessage = e.getMessage();
        String message = e.getMessage().getContentRaw();
    }
}
