package monckey.chopper.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ConvertFile {

    File convertToFile(MultipartFile file, String filename) throws IOException;
}
