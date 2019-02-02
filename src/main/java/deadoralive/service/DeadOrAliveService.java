package deadoralive.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import deadoralive.dto.PersonDTO;
import deadoralive.utils.DeadOrAliveVersion;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class DeadOrAliveService {

  List<PersonDTO> people;
  List<PersonDTO> peopleGoT;
  private static ResourceBundle rb = ResourceBundle.getBundle("app");

  private static DeadOrAliveService ourInstance = new DeadOrAliveService();

  public static DeadOrAliveService getInstance() {
    return ourInstance;
  }

  private DeadOrAliveService() {
    people = readPeople("/people.json");
    peopleGoT = readPeople("/peopleGoT.json");
  }

  private List<PersonDTO> readPeople(String file) {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<PersonDTO>> typeReference = new TypeReference<List<PersonDTO>>() {
    };
    InputStream inputStream = TypeReference.class.getResourceAsStream(file);
    try {
      return mapper.readValue(inputStream, typeReference);
    } catch (IOException e) {
      System.out.println("Error reading persons: " + e.getMessage());
      return new ArrayList<>();
    }
  }

  public PersonDTO retrievePerson(DeadOrAliveVersion version) {
    if (version.equals(DeadOrAliveVersion.GAME_OF_THRONES)) {
      return people.get(new Random().nextInt(people.size()));
    }
    else {
      return peopleGoT.get(new Random().nextInt(peopleGoT.size()));
    }
  }

  public String getSkillId() {
    return rb.getString("skillId");
  }

  public Integer getNumQuestions() {
    return Integer.parseInt((String)rb.getString("numQuestions"));
  }
}
