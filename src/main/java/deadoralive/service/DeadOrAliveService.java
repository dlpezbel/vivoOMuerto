package deadoralive.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import deadoralive.dto.PersonDTO;

public class DeadOrAliveService {

  List<PersonDTO> people;
  private static DeadOrAliveService ourInstance = new DeadOrAliveService();

  public static DeadOrAliveService getInstance() {
    return ourInstance;
  }

  private DeadOrAliveService() {
  }

  private void readPeople() {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<PersonDTO>> typeReference = new TypeReference<List<PersonDTO>>() {
    };
    InputStream inputStream = TypeReference.class.getResourceAsStream("/people.json");
    try {
      people = mapper.readValue(inputStream, typeReference);
    } catch (IOException e) {
      System.out.println("Error reading persons: " + e.getMessage());
    }
  }

  public PersonDTO retrievePerson() {
    if (people == null) {
      readPeople();
    }
    Random rand = new Random();
    PersonDTO personDTO = people.get(rand.nextInt(people.size()));
    return personDTO;
  }

}
