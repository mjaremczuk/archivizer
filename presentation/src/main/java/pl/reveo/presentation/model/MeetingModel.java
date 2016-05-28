package pl.reveo.presentation.model;

import android.os.Parcel;

import java.util.List;

import lombok.Data;

/**
 * Class that represents a fetchMeeting in the presentation layer.
 */

@Data
public class MeetingModel implements android.os.Parcelable {
  private int id;

  private String name;

  private float lat;

  private float lng;

  private List<UserModel> users;

  private int meetingTime;



	public MeetingModel() {

	}

	protected MeetingModel(Parcel in) {
	}

	public static final Creator<MeetingModel> CREATOR = new Creator<MeetingModel>() {
		@Override
		public MeetingModel createFromParcel(Parcel in) {
			return new MeetingModel(in);
		}

		@Override
		public MeetingModel[] newArray(int size) {
			return new MeetingModel[size];
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

