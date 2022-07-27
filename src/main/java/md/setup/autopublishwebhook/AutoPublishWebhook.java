package md.setup.autopublishwebhook;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.NewsChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

public class AutoPublishWebhook extends ListenerAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(AutoPublishWebhook.class);

  public static void main(String[] args) throws LoginException {
    JDABuilder
       .createDefault(System.getenv("discord.token"))
       .setEnabledIntents(GatewayIntent.MESSAGE_CONTENT)
       .addEventListeners(new AutoPublishWebhook())
       .build();
  }

  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    if(!(event.getChannel() instanceof NewsChannel channel)) return;
    if(event.getMessage().isWebhookMessage()) channel.crosspostMessageById(event.getMessageId()).queue();
    LOGGER.info("Published message {} in channel {}.", event.getMessageId(), channel.getId());
  }
}
