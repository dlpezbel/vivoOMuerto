package deadoralive.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;
import deadoralive.DeadOrAliveStreamHandler;


public class CancelandStopIntentHandler implements RequestHandler {

  public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
  }

  public Optional<Response> handle(HandlerInput input) {
    String speechText = "Hasta la próxima";
    return input.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard(DeadOrAliveStreamHandler.SKILL_TITLE, speechText)
        .withShouldEndSession(true)
        .build();
  }
}
