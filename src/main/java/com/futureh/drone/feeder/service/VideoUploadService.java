package com.futureh.drone.feeder.service;

import com.futureh.drone.feeder.exception.WrongInputDataException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * VideoUploadService class.
 */
@Service
public class VideoUploadService {

  /**
   * SaveFile method.
   */
  public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
    Path uploadDirectory = Paths.get("videos-uploads");
    String uri = "/drone/downloadVideo/" + fileName;

    try {
      InputStream inputStream = multipartFile.getInputStream();
      Path filePath = uploadDirectory.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException err) {
      throw new WrongInputDataException("The param don't have a video (Param must have a video).");
    }

    return uri;
  }

}
