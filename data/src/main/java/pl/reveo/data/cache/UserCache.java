package pl.reveo.data.cache;

import android.content.Context;
import pl.reveo.data.cache.iface.IUserCache;
import pl.reveo.data.toolkit.FileManager;
import pl.reveo.domain.executor.ThreadExecutor;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link UserCache} implementation.
 */
@Singleton
public class UserCache implements IUserCache {

	private final Context context;
	private final FileManager fileManager;
	private final ThreadExecutor threadExecutor;

	/**
	 * Constructor of the class {@link UserCache}.
	 *
	 * @param context context
	 * @param fileManager {@link FileManager} for saving serialized objects to the file system.
	 */
	@Inject
	public UserCache(Context context, FileManager fileManager, ThreadExecutor executor) {
		if (context == null || fileManager == null || executor == null) {
			throw new IllegalArgumentException("Invalid null parameter");
		}
		this.context = context.getApplicationContext();
		this.fileManager = fileManager;
		this.threadExecutor = executor;
	}
}
