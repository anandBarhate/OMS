package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TargetFilesController {
    FileManager fileManager = new FileManager();
    @RequestMapping("/file")
    public List<FileStructure> retrieveFiles() {
        return fileManager.retrieveFiles();

    }

    @RequestMapping("/filedata")
    public List<Record> readFileData() {
        List<Record> vehicleNumbers = new ArrayList<Record>();
        List<FileStructure> files = fileManager.retrieveFiles();
        files.forEach(file -> {
            vehicleNumbers.addAll (fileManager.readFile(file.getPath()));
            }
        );

        return vehicleNumbers;
    }
}
