package deadoralive.dto;

import lombok.Data;

@Data
public class PersonDTO {
  Integer id;
  String name;
  Boolean alive;
  String description;
}
