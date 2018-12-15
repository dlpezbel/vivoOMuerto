package deadoralive.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;
import deadoralive.DeadOrAliveStreamHandler;

public class HelpIntentHandler implements RequestHandler {

  public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("AMAZON.HelpIntent"));
  }

  public Optional<Response> handle(HandlerInput input) {
    String speechText = "Pensaré en una letra y en palabras que no la contengan, "
        + "comenzaré a jugar diciendo una palabra que no contenga la letra y por lo tanto que pasa la frontera."
        + " Los jugadores irán diciendo palabras y yo decidiré si pasa o no la frontera."
        + " Iré dejando pasar o no la frontera según se cumpla o no la norma. Gana quien descubre la letra";
    return input.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard(DeadOrAliveStreamHandler.SKILL_TITLE, speechText)
        .withReprompt(speechText)
        .build();
  }
}