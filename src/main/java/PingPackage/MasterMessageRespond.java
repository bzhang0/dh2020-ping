package PingPackage;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.EmptyStackException;
import java.util.Scanner;

public class MasterMessageRespond extends ListenerAdapter {

    private String PREFIX = ".";

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        messageLog(e);

        User author = e.getAuthor();
        Member member = e.getMember();

        Message message = e.getMessage();
        String msg = message.getContentRaw();

        TextChannel channel = e.getChannel();

        System.out.println("Message sent by " + author.getName() + " [" +
                author.getId() + "]: " + message.getContentRaw());

        if (msg.length() > PREFIX.length() && msg.startsWith(PREFIX) && !e.getAuthor().isBot()) {
            long startTime = System.nanoTime();
            Scanner sentMessageInput = new Scanner(msg.substring(PREFIX.length()));

            String authorID = author.getId();
            String prompt = sentMessageInput.next();
//            e.getMember().get

//            if (prompt.equalsIgnoreCase("quit") && ;
        }
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

    public void messageLog(GuildMessageReceivedEvent e) {
        System.out.println("Message in " + e.getGuild().getName() + " sent by " +
                e.getAuthor().getName() + " in " + e.getChannel().getName() +
                ": " + e.getMessage().getContentRaw() + " ");
    }
}
