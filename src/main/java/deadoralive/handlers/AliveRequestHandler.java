package deadoralive.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import java.util.Map;
import java.util.Optional;

public class AliveRequestHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(Predicates.intentName("AliveIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {

    String correctSpeechText = "Correcto, es la letra ";
    String incorrectSpeechText = "Incorrecto, es la letra ";
    String playNotInitializated = "debe empezar el juego diciendo pasa la frontera";

    IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
    Map<String, Slot> slots = intentRequest.getIntent().getSlots();
    String letterSend = slots.get("letra").getValue();

    Optional<String> letter = Optional
        .ofNullable((String) input.getAttributesManager().getSessionAttributes().get("letra"));
    String speechText;
    if (!letter.isPresent()) {
      speechText = playNotInitializated;
    } else if (letter.equals(letterSend)) {
      speechText = correctSpeechText + letter.get() + ". Hasta la proxima.";
    } else {
      speechText = incorrectSpeechText + letter.get() + ". Hasta la proxima.";
    }
    return input.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard("SKILL_TITLE", speechText)
        .withShouldEndSession(true)
        .build();
  }
}

