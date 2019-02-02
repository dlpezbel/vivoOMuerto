package deadoralive;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import deadoralive.handlers.AliveRequestHandler;
import deadoralive.handlers.CancelandStopIntentHandler;
import deadoralive.handlers.DeadOrAliveRequestHandler;
import deadoralive.handlers.DeadRequestHandler;
import deadoralive.handlers.HelpIntentHandler;
import deadoralive.handlers.LaunchRequestHandler;
import deadoralive.handlers.SessionEndedRequestHandler;
import deadoralive.service.DeadOrAliveService;

public class DeadOrAliveStreamHandler extends SkillStreamHandler {

  public static final String SKILL_TITLE = "Vivo o muerto";

  private static Skill getSkill() {

    return Skills
        .standard()
        .addRequestHandlers(
            new LaunchRequestHandler(),
            new CancelandStopIntentHandler(),
            new SessionEndedRequestHandler(),
            new HelpIntentHandler(),
            new AliveRequestHandler(),
            new DeadOrAliveRequestHandler(),
            new DeadRequestHandler()
        )
        .withSkillId(DeadOrAliveService.getInstance().getSkillId())
        .build();
  }

  public DeadOrAliveStreamHandler() {
    super(getSkill());
  }

}