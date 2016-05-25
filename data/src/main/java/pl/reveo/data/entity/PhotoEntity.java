package pl.reveo.data.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Photo entity in the data layer.
 */
@Data
public class PhotoEntity {
  @SerializedName("id")
  private int id;

  @SerializedName("approved")
  private int approved;

  @SerializedName("deleted")
  private int deleted;

  @SerializedName("likes")
  private int likes;

  @SerializedName("thumnbail")
  private String thumnbail;

  @SerializedName("full")
  private String full;


}
