package pl.reveo.domain;

import lombok.Data;

/**
 * Class that represents a Photo in the domain layer.
 */
@Data
public class Photo {
  private int id;

  private int approved;

  private int deleted;

  private int likes;

  private String thumnbail;

  private String full;


}
