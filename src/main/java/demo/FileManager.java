package demo;


import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.assertj.core.util.VisibleForTesting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ConfigurationProperties

public class FileManager {

    private static final Logger LOGGER = Logger.getLogger(FileManager.class.getName());
    @Value("${directory}")
    private String directory;
    @Value("${pattern}")
    private String pattern;




    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<FileStructure> retrieveFiles()  {
        //DirectoryStream<Path> children = Files.newDirectoryStream(Paths.get("C:\\Users\\anandbar"), path -> path.toFile().isFile());;
        pattern="*.{xls,csv}";
        directory="/Users/anandbarhate/test";

        List<FileStructure> result = new ArrayList<FileStructure>();
        LOGGER.log(Level.INFO ,"Reading properties from {0}",directory);

        try {
            List<Path> fileList = listSourceFiles(Paths.get(directory));

            fileList.forEach(file -> {
                        try {
                            result.add(new FileStructure(file.getFileName().toString(),
                                                           FilenameUtils.getExtension(file.getFileName().toString()),
                                                           file.toFile().length(),
                                                           Files.probeContentType(file.getFileName()),
                                                           file.toAbsolutePath()
                                                          )
                            );

                        } catch (IOException e) {
                            LOGGER.log(Level.SEVERE, "Exception occured ", e);
                        }
                    }
            );
        }
        catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception occured ", e);
        }
        return result;


    }

    @VisibleForTesting
    List<Path> listSourceFiles(Path dir) throws IOException {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, pattern)) {
            for (Path entry: stream) {
                result.add(entry);
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
            throw ex.getCause();
        }
        return result;
    }

    public List<Record> readFile(Path fileWithPath) {
        List<Record> vehileData = new ArrayList<Record>();
        Util util = new Util();
        Iterable<CSVRecord> records = util.readFile(fileWithPath.toAbsolutePath());
        if(null != records) {
            records.forEach(record -> {
                vehileData.add(
                        new Record(
                                record.get("Reg number".toLowerCase()),
                                record.get("make".toLowerCase()),
                                record.get("colour".toLowerCase())
                        )
                );
            });
        }
        return vehileData;
    }

}
