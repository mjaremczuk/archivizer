package pl.reveo.data.cache;

import android.content.Context;
import pl.reveo.data.cache.iface.IMeetingCache;
import pl.reveo.data.toolkit.FileManager;
import pl.reveo.domain.executor.ThreadExecutor;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link MeetingCache} implementation.
 */
@Singleton
public class MeetingCache implements IMeetingCache {

	private final Context context;
	private final FileManager fileManager;
	private final ThreadExecutor threadExecutor;

	/**
	 * Constructor of the class {@link MeetingCache}.
	 *
	 * @param context context
	 * @param fileManager {@link FileManager} for saving serialized objects to the file system.
	 */
	@Inject
	public MeetingCache(Context context, FileManager fileManager, ThreadExecutor executor) {
		if (context == null || fileManager == null || executor == null) {
			throw new IllegalArgumentException("Invalid null parameter");
		}
		this.context = context.getApplicationContext();
		this.fileManager = fileManager;
		this.threadExecutor = executor;
	}
}
