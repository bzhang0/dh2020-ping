package PingPackage;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class PingBotMain {
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder
                .createDefault("NzY3MTUyMTE1ODE0MTA1MTE5.X4twFQ.x6xCy1L_aJVyXrKREiizlVYd42I")
                .addEventListeners(new MessageEventListener())
                .build();
        System.out.println(jda.getInviteUrl());
    }
}
class MasterMessageRespond extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        Message messageMessage = e.getMessage();
        String message = e.getMessage().getContentRaw();

        if (message.equals("!ping")) {
            e.getChannel().sendMessage("pong!").complete();
        }
    }
}
