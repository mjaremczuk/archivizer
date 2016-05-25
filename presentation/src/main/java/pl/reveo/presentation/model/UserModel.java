package pl.reveo.presentation.model;

import android.os.Parcel;
import lombok.Data;

/**
 * Class that represents a fetchUser in the presentation layer.
 */

@Data
public class UserModel implements android.os.Parcelable {
  private int id;

  private String accessToken;

  private String email;

  private String name;

  private String phoneNumber;

  private String ticket;



	public UserModel() {

	}

	protected UserModel(Parcel in) {
	}

	public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
		@Override
		public UserModel createFromParcel(Parcel in) {
			return new UserModel(in);
		}

		@Override
		public UserModel[] newArray(int size) {
			return new UserModel[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}
}

