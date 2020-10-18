package PingPackage;

import jdk.incubator.http.WebSocket;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class PingBotMain extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder
            .createDefault("NzY3MTUyMTE1ODE0MTA1MTE5.X4twFQ.x6xCy1L_aJVyXrKREiizlVYd42I")
            .addEventListeners(new PingBotMain(), new MasterMessageRespond())
            .build();
        System.out.println(jda.getInviteUrl());
    }
<<<<<<< Updated upstream
=======
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
        URL oracle = new URL("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=407CEDDFFC32EE02E865328245752A79&steamid=76561198129178478&include_appinfo=true&format=xml");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
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
>>>>>>> Stashed changes
}
