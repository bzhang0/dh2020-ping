package PingPackage;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.EmptyStackException;

public class MasterMessageRespond extends ListenerAdapter {

    private String PREFIX = ".";

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        Message message = e.getMessage();
        TextChannel channel = e.getChannel();
        String msg = message.getContentRaw();

        System.out.println("Message sent by " + e.getAuthor().getName() + " [" +
                e.getAuthor().getId() + "]: " + message.getContentRaw());

        if (msg.equals(".ping")) {
            channel.sendMessage("pong!").complete();
        }

        if (msg.contains("play")) {
            channel.addReactionById(message.getId(),"\uD83C\uDFAE").complete();
        }

        if (msg.equals(".help")) {
            help(e);
        }
    }

    public void help(GuildMessageReceivedEvent e) {
        EmbedBuilder embed = new EmbedBuilder();

        embed.setDescription("commands are summoned by " + PREFIX + "<command>");

        embed.addField(PREFIX + "blabla", "HI GUBA", false);

        e.getChannel().sendMessage(MasterMessageAction.defaultEmbed(embed, "help", PREFIX).build()).complete();
    }
}
