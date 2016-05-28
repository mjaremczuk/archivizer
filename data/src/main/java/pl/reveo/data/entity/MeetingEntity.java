package pl.reveo.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Meeting entity in the data layer.
 */
@Data
public class MeetingEntity {
  @SerializedName("id")
  private int id;

  @SerializedName("name")
  private String name;

  @SerializedName("lat")
  private float lat;

  @SerializedName("lng")
  private float lng;

  @SerializedName("users")
  private List<UserEntity> users;

  @SerializedName("meeting_time")
  private int meetingTime;


}
