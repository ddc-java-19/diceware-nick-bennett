package edu.cnm.deepdive.diceware.service;

import java.util.List;

public interface PassphraseService {

  List<String> generate(int length);

}
