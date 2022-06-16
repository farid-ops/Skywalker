package monckey.chopper.file;

import java.io.IOException;

public interface DownloadService {

    Object download(String filename) throws IOException;
}
