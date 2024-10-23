package edu.cnm.deepdive.diceware.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicewareService implements PassphraseService {

  private final List<String> words;
  private final RandomGenerator rng;

  @Autowired
  public DicewareService(WordsProvider provider, RandomGenerator rng) {
    words = new ArrayList<>(provider.getWords());
    Collections.shuffle(words, rng);
    this.rng = rng;
  }

  @Override
  public List<String> generate(int length) {
    return IntStream.generate(() -> rng.nextInt(words.size()))
        .limit(length)
        .mapToObj(words::get)
        .toList();
  }

}
