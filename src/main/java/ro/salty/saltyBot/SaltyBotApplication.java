package ro.salty.saltyBot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import ro.salty.saltyBot.listeners.PingListener;

@SpringBootApplication
public class SaltyBotApplication {

	@Autowired
	private Environment env;

	@Autowired
	private PingListener pingListener;

	public static void main(String[] args) { SpringApplication.run(SaltyBotApplication.class, args); }

	@Bean
	@ConfigurationProperties(value = "discord-api")
	public DiscordApi discordApi(){
		String token = env.getProperty("TOKEN");
		DiscordApi api = new DiscordApiBuilder().setToken(token)
				.setAllNonPrivilegedIntents()
				.login()
				.join();

		api.addMessageCreateListener(pingListener);
		return api;
	}

}
