package pl.reveo.data.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.inject.Singleton;
import pl.reveo.data.entity.PhotoEntity;
import pl.reveo.data.entity.UserEntity;
import pl.reveo.data.entity.MeetingEntity;



/**
 * Database helper
 */
@Singleton
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	/**
	 * Database name
	 */
	private static final String DATABASE_NAME = "pl.reveo.db";

	/**
	 * Database version
	 */
	private static final int DATABASE_VERSION = 1;

	/**
	 * Table classes
	 */
	private Class[] tableClasses = {
			PhotoEntity.class,
			UserEntity.class,
			MeetingEntity.class,

	};

	/**
	 * Photo entity DAO
	 */
	private Dao<PhotoEntity, Integer> photoDao;

	/**
	 * User entity DAO
	 */
	private Dao<UserEntity, Integer> userDao;

	/**
	 * Meeting entity DAO
	 */
	private Dao<MeetingEntity, Integer> meetingDao;



	/**
	 * Constructor
	 *
	 * @param context context
	 */
	@Inject
	public DatabaseHelper(Context context) {
		super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Creates database instance
	 *
	 * @param database databse
	 * @param connectionSource connection source
	 */
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			for (int i = 0; i < tableClasses.length; i++) {
				TableUtils.createTable(connectionSource, tableClasses[i]);
			}
		}
		catch (SQLException sqlException) {
//			Timber.d("Can't create database");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			for (int i = tableClasses.length - 1; i >= 0; i--) {
				TableUtils.dropTable(connectionSource, tableClasses[i], true);
			}

			onCreate(database, connectionSource);
		}
		catch (SQLException sqlException) {
//			Timber.d("Can't drop database");
		}
	}

	public Dao<PhotoEntity, Integer> getPhotoDao() {
		if (photoDao == null) {
			try {
				photoDao = getDao(PhotoEntity.class);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return photoDao;
	}

	public Dao<UserEntity, Integer> getUserDao() {
		if (userDao == null) {
			try {
				userDao = getDao(UserEntity.class);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userDao;
	}

	public Dao<MeetingEntity, Integer> getMeetingDao() {
		if (meetingDao == null) {
			try {
				meetingDao = getDao(MeetingEntity.class);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return meetingDao;
	}


}
