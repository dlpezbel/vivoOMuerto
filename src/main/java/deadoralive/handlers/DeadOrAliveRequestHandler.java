package deadoralive.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import java.util.Optional;
import deadoralive.DeadOrAliveStreamHandler;

public class DeadOrAliveRequestHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(Predicates.intentName("VivoOMuertoIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {
    String letra = "";
    String palabraInicial = "";
    String speechText = "Comenzamos el juego. La palabra " + palabraInicial + " pasa la frontera.";
    input.getAttributesManager().getSessionAttributes().clear();
    input.getAttributesManager().getSessionAttributes().put("letra", letra);
    return input.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard(DeadOrAliveStreamHandler.SKILL_TITLE, speechText)
        .withReprompt(speechText)
        .build();
  }

}

