package md.setup.autopublishwebhook;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.NewsChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public class AutoPublishWebhook extends ListenerAdapter {

  public static final Logger LOGGER = LoggerFactory.getLogger(AutoPublishWebhook.class);

  public static void main(String[] args) throws LoginException, InterruptedException {
    JDA jda = JDABuilder
       .createDefault(System.getProperty("discord.token"))
       .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
       .addEventListeners(new AutoPublishWebhook())
       .setStatus(OnlineStatus.DO_NOT_DISTURB)
       .setActivity(Activity.watching("the news"))
       .build();
    jda.awaitReady();

    jda.addEventListener(new PublishListener());
  }



}
