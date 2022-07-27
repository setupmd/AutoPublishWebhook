package md.setup.autopublishwebhook;

import net.dv8tion.jda.api.entities.NewsChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static md.setup.autopublishwebhook.AutoPublishWebhook.LOGGER;

public class PublishListener extends ListenerAdapter {

  @Override
  public void onMessageReceived(@NotNull MessageReceivedEvent event) {
    if(!(event.getChannel() instanceof NewsChannel channel)) return;
    if(!event.getMessage().isWebhookMessage()) return;
    channel.crosspostMessageById(event.getMessageId()).queue(
       msg -> LOGGER.info("Crossposted message {} from channel {}", msg.getId(), channel.getId())
    );
  }
}
