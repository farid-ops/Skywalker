package monckey.chopper.api;

import monckey.chopper.file.DownloadService;
import monckey.chopper.file.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileApi {

    private final UploadService uploadService;
    private final DownloadService downloadService;

    public FileApi(UploadService uploadService,
                   DownloadService downloadService){
        this.uploadService = uploadService;
        this.downloadService = downloadService;
    }

    private final static Logger logger = LoggerFactory.getLogger(FileApi.class);

    @PostMapping("/profile/pic")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return this.uploadService.upload(multipartFile);
    }

    @PostMapping("/profile/pic/{fileName}")
    public Object download(@PathVariable String fileName) throws IOException {
        logger.info("HIT -/download | File Name : {}", fileName);
        return this.downloadService.download(fileName);
    }
}
