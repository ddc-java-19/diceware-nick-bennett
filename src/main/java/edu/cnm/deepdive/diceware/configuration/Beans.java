package edu.cnm.deepdive.diceware.configuration;

import java.security.SecureRandom;
import java.util.random.RandomGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

  @Bean
  public RandomGenerator provideRng() {
    SecureRandom rng = new SecureRandom();
    rng.reseed();
    return rng;
  }

}
