package pl.reveo.data.toolkit;

import java.io.File;

/**
 * {@link Runnable} class for writing to disk.
 */
public class CacheWriter implements Runnable {

	private final FileManager fileManager;
	private final File fileToWrite;
	private final String fileContent;

	public CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
		this.fileManager = fileManager;
		this.fileToWrite = fileToWrite;
		this.fileContent = fileContent;
	}

	@Override
	public void run() {
		fileManager.writeToFile(fileToWrite, fileContent);
	}
}