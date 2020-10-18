package PingPackage;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class PingBotMain extends ListenerAdapter {
    private static String tempSteamID;
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder
                .createDefault("NzY3MTUyMTE1ODE0MTA1MTE5.X4twFQ.x6xCy1L_aJVyXrKREiizlVYd42I")
                .addEventListeners(
                        new PingBotMain(),
                        new MasterMessageRespond())
                .build();
        try {
            readSteamAPI();
        } catch (Exception e) {
            System.out.println("Failed reading Steam API");
            e.printStackTrace();
        }
        System.out.println(jda.getInviteUrl());
    }
    // Takes an input file and checks if it exists
    // Promptly returns it to another method for later use
    private static File identifyFile() {
        try {
            File inputFile = new File("inputFile.txt");
            if (inputFile.createNewFile()) {
                System.out.println("Debug : File Created");
            } else {
                System.out.println("Debug : File Exists");
            }
            return inputFile;
        } catch (IOException e) {
            System.out.println("Error reading input");
            e.printStackTrace();
        }
        return null;
    }
    // Reads the Steam API given by Gabe using BufferedReader
    // Method includes parser to indicate name of owned games with subsequent play times (in minutes)
    private static void readSteamAPI() throws IOException {
        URL oracle = new URL("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=407CEDDFFC32EE02E865328245752A79&steamid="
                + tempSteamID
                + "&include_appinfo=true&format=xml");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
            if (inputLine.contains("<name>")) {
                String name;
                name = inputLine.replaceAll("<name>", "");
                name = name.replaceAll("</name>", "");
                if (name.contains("&apos;"))
                    name = name.replaceAll("&apos;", "'");
                System.out.println(name.trim());
            }
            if (inputLine.contains("<playtime_forever>")) {
                String playtime;
                playtime = inputLine.replaceAll("<playtime_forever>", "");
                playtime = playtime.replaceAll("</playtime_forever>", "");
                if (playtime.contains("&apos;"))
                    playtime = playtime.replaceAll("&apos;", "'");
                System.out.println(playtime.trim());
            }
        }
        in.close();
    }
    public static boolean grabSteamID(String id) {
        tempSteamID = id;
        try {
            readSteamAPI();
            return true;
        } catch (Exception e) {
            System.out.println("Invalid ID");
            e.printStackTrace();
            return false;
        }
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
        if (message.startsWith("!connectsteam")) {
            message = message.replaceAll("!connectsteam", "");
            message = message.trim();
            if (PingBotMain.grabSteamID(message)) {
                e.getChannel().sendMessage("Invalid SteamID").complete();
            }
        }
    }
}
