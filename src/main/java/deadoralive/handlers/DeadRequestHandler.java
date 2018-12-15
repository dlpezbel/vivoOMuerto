package deadoralive.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import java.util.Map;
import java.util.Optional;
import deadoralive.DeadOrAliveStreamHandler;

public class DeadRequestHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(Predicates.intentName("PalabraPasaLaFronteraIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {

    String pasaLaFrontera = "pasa la frontera";
    String noPasaLaFrontera = "no pasa la frontera";
    String juegoNoIniciado = "debe empezar el juego diciendo pasa la frontera";

    IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
    Map<String, Slot> slots = intentRequest.getIntent().getSlots();
    String palabra = slots.get("palabra").getValue();

    String speechText;
    String letra = (String) input.getAttributesManager().getSessionAttributes().get("letra");
    if (letra == null || letra.isEmpty()) {
      speechText = juegoNoIniciado;
    } else if (palabra.contains(letra)) {
      speechText = noPasaLaFrontera;
    } else {
      speechText = pasaLaFrontera;
    }

    return input.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard(DeadOrAliveStreamHandler.SKILL_TITLE, speechText)
        .withReprompt(speechText)
        .build();
  }
}

