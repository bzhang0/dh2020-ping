package PingPackage;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
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
