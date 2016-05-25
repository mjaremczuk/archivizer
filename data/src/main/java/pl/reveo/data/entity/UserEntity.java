package pl.reveo.data.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * User entity in the data layer.
 */
@Data
public class UserEntity {
  @SerializedName("id")
  private int id;

  @SerializedName("access_token")
  private String accessToken;

  @SerializedName("email")
  private String email;

  @SerializedName("name")
  private String name;

  @SerializedName("phone_number")
  private String phoneNumber;

  @SerializedName("ticket")
  private String ticket;


}
