package tv.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

public class FileUploadUtil {
	private static final String UPLOAD_DIR = "/WEB-INF/uploads";

	public static String saveFile(InputStream fileContent, String fileName, ServletContext servletContext)
			throws IOException {
		String uploadPath = servletContext.getRealPath(UPLOAD_DIR);
		Path uploadDirPath = Paths.get(uploadPath);

		if (!Files.exists(uploadDirPath)) {
			Files.createDirectories(uploadDirPath);
		}

		Path filePath = uploadDirPath.resolve(fileName);

		if (Files.exists(filePath)) {
			throw new IOException("File already exists: " + filePath);
		}

		Files.copy(fileContent, filePath);
		
		fileContent.close();

		return fileName;
	}
}
