package ro.salty.saltyBot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SaltyBotApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) { SpringApplication.run(SaltyBotApplication.class, args); }

	@Bean
	@ConfigurationProperties(value = "discord-api")
	public DiscordApi discordApi(){
		String token = env.getProperty("TOKEN");
		DiscordApi api = new DiscordApiBuilder().setToken(token)
				.setAllNonPrivilegedIntents()
				.login()
				.join();

		api.addMessageCreateListener(event -> {
			if(event.getMessageContent().equals(".ping")){
				event.getChannel().sendMessage("Pong!");
			}
			if(event.getMessageContent().equals(".melon")){
				event.getChannel().sendMessage("What did the melon say when his lawn looked dry.\n" +
						"Guess it's time to watermalawn.");
			}
		});
		return api;
	}

}
