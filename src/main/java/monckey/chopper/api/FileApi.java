package monckey.chopper.api;

import monckey.chopper.file.DownloadService;
import monckey.chopper.file.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/file")
public class FileApi {

    private final UploadService uploadService;
    private final DownloadService downloadService;

    public FileApi(UploadService uploadService,
                   DownloadService downloadService){
        this.uploadService = uploadService;
        this.downloadService = downloadService;
    }

    private final static Logger logger = LoggerFactory.getLogger(FileApi.class);

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return this.uploadService.upload(multipartFile);
    }

    @PostMapping("/download/{fileName}")
    public Object download(@PathVariable String fileName) throws IOException {
        logger.info("HIT -/download | File Name : {}", fileName);
        return this.downloadService.download(fileName);
    }
}
