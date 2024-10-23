package edu.cnm.deepdive.diceware.controller;

import edu.cnm.deepdive.diceware.service.PassphraseService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/passphrases")
@Validated
public class PassphraseController {

  private final PassphraseService passphraseService;

  @Autowired
  public PassphraseController(PassphraseService passphraseService) {
    this.passphraseService = passphraseService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<String> get(
      @RequestParam(required = false, defaultValue = "5")
      @Min(1)
      @Max(12)
      int length
  ) {
    return passphraseService.generate(length);
  }

  @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
  public String get(
      Model model,
      @RequestParam(required = false, defaultValue = "5")
      @Min(1)
      @Max(12)
      int length
  ) {
    model.addAttribute("words", get(length));
    return "passphrases";
  }

  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String get(
      @RequestParam(required = false, defaultValue = "5")
      @Min(1)
      @Max(12)
      int length,
      @RequestParam(required = false, defaultValue = " ")
      String delimiter
  ) {
    return String.join(delimiter, get(length));
  }

}
