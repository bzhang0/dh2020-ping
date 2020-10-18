package PingPackage;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterGameAction extends ListenerAdapter {

    public static ArrayList<ArrayList<String>> database = new ArrayList<>();

    public static void storeUsers(Guild e) {
        List<Member> members = e.getMembers();
        System.out.print(members);
        for (int i = 0; i < members.size(); i++) {
            database.get(i).add(members.get(i).getId());
            addRandomGames(members, i);
        }
    }

    public static void ownedGames(GuildMessageReceivedEvent e) {
        String memberID = e.getMember().getId();

        EmbedBuilder embed = new EmbedBuilder();

        embed.setDescription(e.getMember().getEffectiveName());

        for (int i = 1; i < database.get(returnIndex(memberID)).size(); i++) {
            embed.addField(database.get(returnIndex(memberID)).get(i), "", false);
        }

        e.getChannel().sendMessage(MasterMessageAction.defaultEmbed(embed,
                e.getMember().getEffectiveName(),
                MasterMessageRespond.PREFIX).build()).complete();
    }

    public static void addRandomGames(List<Member> members, int index) {
        String[] randomGames = {"among us", "counter strike: global offensive", "fortnite",
                "valorant", "league of legends", "terraria"};

        String memberID = members.get(index).getId();
        for (String game : randomGames) {
            if (new Random().nextInt(2) == 0) {
                database.get(returnIndex(memberID)).add(game);
            }
        }
    }

    public static int returnIndex(String memberID) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).contains(memberID)) {
                return i;
            }
        }

        return -1;
    }
}