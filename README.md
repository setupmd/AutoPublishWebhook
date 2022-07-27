# AutoPublishWebhook
An insanely simple Discord bot to automatically publish webhook messages sent in news channels.

### Compiling

```bash
$ mvn install
$ mvn package
```

File will be in `target/AutoPublishWebhook.jar`.

### Running

Use the `-Ddiscord.token` flag to specify the Discord token.

```bash
$ java -Ddiscord.token=<token> -jar target/AutoPublishWebhook.jar
```