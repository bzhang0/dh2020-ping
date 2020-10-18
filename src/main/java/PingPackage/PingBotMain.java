package PingPackage;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class PingBotMain extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder
            .createDefault("NzY3MTUyMTE1ODE0MTA1MTE5.X4twFQ.x6xCy1L_aJVyXrKREiizlVYd42I")
            .addEventListeners(new PingBotMain(), new MasterMessageRespond())
            .build();
        System.out.println(jda.getInviteUrl());
    }
}
