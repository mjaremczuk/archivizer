package pl.reveo.domain;

import lombok.Data;

/**
 * Class that represents a User in the domain layer.
 */
@Data
public class User {
  private int id;

  private String accessToken;

  private String email;

  private String name;

  private String phoneNumber;

  private String ticket;


}
