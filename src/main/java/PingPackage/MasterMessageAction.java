package PingPackage;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class MasterMessageAction extends ListenerAdapter {
    private static final Random r = new Random();

//    private static String iconURL = "https://cdn.discordapp.com/attachments/512508615207419904/532003603414646815/m1-250x250.jpg";

    public static EmbedBuilder defaultEmbed(EmbedBuilder embed, String title, String prefix) {
        return rColorEmbed(embed).setAuthor(title)
                .setFooter(prefix + "help")
                .setTimestamp(LocalDateTime.now());
    }

    public static EmbedBuilder rColorEmbed(EmbedBuilder embed) {
        return embed.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
    }
}
