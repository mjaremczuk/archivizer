package pl.reveo.presentation.model;

import android.os.Parcel;
import lombok.Data;

/**
 * Class that represents a fetchPhoto in the presentation layer.
 */

@Data
public class PhotoModel implements android.os.Parcelable {
  private int id;

  private int approved;

  private int deleted;

  private int likes;

  private String thumnbail;

  private String full;



	public PhotoModel() {

	}

	protected PhotoModel(Parcel in) {
	}

	public static final Creator<PhotoModel> CREATOR = new Creator<PhotoModel>() {
		@Override
		public PhotoModel createFromParcel(Parcel in) {
			return new PhotoModel(in);
		}

		@Override
		public PhotoModel[] newArray(int size) {
			return new PhotoModel[size];
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

