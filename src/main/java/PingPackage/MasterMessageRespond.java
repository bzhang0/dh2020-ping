package PingPackage;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class MasterMessageRespond extends ListenerAdapter {

    public static String PREFIX = ".";

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        messageLog(e);
        MasterGameAction.storeUsers(e.getGuild());

//        User author = e.getAuthor();
        Member member = e.getMember();

        Message message = e.getMessage();
        String msg = message.getContentRaw();

        TextChannel channel = e.getChannel();

        if (msg.length() > PREFIX.length() && msg.startsWith(PREFIX) && !e.getAuthor().isBot()) {
            long startTime = System.nanoTime();
            Scanner sentMessageInput = new Scanner(msg.substring(PREFIX.length()));

            String prompt = sentMessageInput.next();
//            List<Role> roles = e.getMember().getRoles();

//            for (Role r : roles) {
//                EnumSet<Permission> p = r.getPermissions();
//
//                if (p.contains(Permission.ADMINISTRATOR)) {
//                    System.out.println("YEAHHHH");
//                }
//                System.out.println(r + " " + p);
//            }

            if (prompt.equalsIgnoreCase("quit") && member.getPermissions().contains(Permission.ADMINISTRATOR)) {
                // save data
                channel.sendMessage("shutting down...").complete();
                System.exit(1);
            } else if (prompt.equalsIgnoreCase("version")) {
                channel.sendMessage(PingBotMain.VERSION).complete();
            } else if (prompt.equalsIgnoreCase("ping")) {
                EmbedBuilder embed = new EmbedBuilder();

                String programTimeString = ("" + ((double)(System.nanoTime() - startTime)) / 1000000.0).substring(0, 5);
                embed.setTitle("pong!").setFooter("returned in " + programTimeString + "ms");
//                channel.sendMessage("pong!");

                channel.sendMessage(embed.build()).complete();
            } else if (prompt.equalsIgnoreCase("help")) {
                help(e);
            } else if (prompt.equalsIgnoreCase("mygames")) {
                MasterGameAction.ownedGames(e);
            }
        }

        msg = msg.toLowerCase();
        if (msg.contains("play")) {
            channel.addReactionById(message.getId(), "\uD83C\uDFAE").complete();
        }
    }

    public void help(GuildMessageReceivedEvent e) {
        EmbedBuilder embed = new EmbedBuilder();

        embed.setDescription("commands are summoned by " + PREFIX + "<command>")
                .addField(PREFIX + "quit", "shuts down the bot", false)
                .addField(PREFIX + "ping", "returns pong!", false);

        e.getChannel().sendMessage(MasterMessageAction.defaultEmbed(embed, "help", PREFIX).build()).complete();
    }

    public void messageLog(GuildMessageReceivedEvent e) {
        System.out.println("Message in " + e.getGuild().getName() + " sent by " +
                e.getAuthor().getName() + " [" + e.getAuthor().getId() + "] in " + e.getChannel().getName() +
                ": " + e.getMessage().getContentRaw() + " ");
    }
}