package pl.reveo.domain;

import lombok.Data;

/**
 * Class that represents a Meeting in the domain layer.
 */
@Data
public class Meeting {
  private int id;

  private String name;

  private float lat;

  private float lng;

  private List<User> users;

  private int meetingTime;


}
