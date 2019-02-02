package deadoralive.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import deadoralive.dto.PersonDTO;
import deadoralive.service.DeadOrAliveService;
import deadoralive.utils.DeadOrAliveVersion;
import java.util.Map;
import java.util.Optional;
import deadoralive.DeadOrAliveStreamHandler;

public class DeadOrAliveRequestHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(Predicates.intentName("VivoOMuertoIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {

    IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
    Map<String, Slot> slots = intentRequest.getIntent().getSlots();
    String versionType = slots.get("version_type").getValue();
    PersonDTO person = DeadOrAliveService.getInstance()
        .retrievePerson(DeadOrAliveVersion.FAMOUS_PEOPLE);
    String speechText = "De acuerdo. ¿" + person.getName() + " está viva o muerta?";
    input.getAttributesManager().getSessionAttributes().clear();
    input.getAttributesManager().getSessionAttributes().put("version", versionType);
    input.getAttributesManager().getSessionAttributes().put("currentPerson", person);
    return input.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard(DeadOrAliveStreamHandler.SKILL_TITLE, speechText)
        .withReprompt(speechText)
        .build();
  }

}

