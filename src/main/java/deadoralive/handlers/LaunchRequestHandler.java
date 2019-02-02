package deadoralive.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import java.util.Optional;
import deadoralive.DeadOrAliveStreamHandler;

public class LaunchRequestHandler implements RequestHandler {

  public boolean canHandle(HandlerInput input) {
    return input.matches(requestType(LaunchRequest.class));
  }

  public Optional<Response> handle(HandlerInput input) {
    String speechText = "Bienvenido al juego de \"Vivo o muerto\". ¿A qué versión quieres jugar: versión personajes famosos o versión juego de tronos?";

    return input.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard(DeadOrAliveStreamHandler.SKILL_TITLE, speechText)
        .withReprompt(speechText)
        .build();
  }
}
