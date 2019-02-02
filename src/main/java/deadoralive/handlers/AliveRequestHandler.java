package deadoralive.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import deadoralive.DeadOrAliveStreamHandler;
import deadoralive.dto.PersonDTO;
import java.util.Optional;

public class AliveRequestHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(Predicates.intentName("AliveIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {

    String correctSpeechText = "Correcto";
    String incorrectSpeechText = "Incorrecto";

    Optional<PersonDTO> currentPerson = Optional
        .ofNullable(
            (PersonDTO) input.getAttributesManager().getSessionAttributes().get("currentPerson"));
    String speechText = "";
    boolean isGameFinished = false;

    return input.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard(DeadOrAliveStreamHandler.SKILL_TITLE, speechText)
        .withShouldEndSession(isGameFinished)
        .build();
  }
}

