package pl.reveo.data.toolkit;

import java.io.File;

/**
 * {@link Runnable} class for evicting all the cached files
 */
public class CacheEvictor implements Runnable {

	private final FileManager fileManager;
	private final File cacheDir;

	public CacheEvictor(FileManager fileManager, File cacheDir) {
		this.fileManager = fileManager;
		this.cacheDir = cacheDir;
	}

	@Override
	public void run() {
		fileManager.clearDirectory(this.cacheDir);
	}
}
