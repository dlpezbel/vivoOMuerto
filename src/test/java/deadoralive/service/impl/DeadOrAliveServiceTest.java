package deadoralive.service.impl;

import org.junit.jupiter.api.Test;
import deadoralive.dto.PersonDTO;
import deadoralive.service.DeadOrAliveService;

public class DeadOrAliveServiceTest {
  @Test
  public void retrievePersonTest() {
    DeadOrAliveService deadOrAliveService = DeadOrAliveService.getInstance();
    PersonDTO personDTO = deadOrAliveService.retrievePerson();
    System.out.println(personDTO);
  }
}
