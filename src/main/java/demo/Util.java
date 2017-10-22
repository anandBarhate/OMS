package demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.assertj.core.util.VisibleForTesting;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by anandbarhate on 21/10/2017.
 */
public class Util
{
    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    CSVFormat csvFormat;



    public Iterable<CSVRecord> readFile(Path path) {
        Iterable<CSVRecord> records = null;
        try {
            LOGGER.log(Level.INFO, "Reading file {0}", path.toAbsolutePath());
            Reader in = new FileReader(path.toAbsolutePath().toFile());
            final CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader());
            if(validateFile(parser)) {
                records = parser.getRecords();
            }
            else {
                LOGGER.log(Level.INFO, "File {0} is not valid", path.toAbsolutePath());
            }

        }
        catch(Exception e) {
            // any exception in this process is treated as server and there is no processing done
            // based on different types of exception hence catch basic exception
            LOGGER.log(Level.SEVERE,"Can not parse file, Exception occured ", e);

        }
        return records;
    }

    /*
        Validates file
     */
    @VisibleForTesting
     boolean validateFile(CSVParser parser) {
        Map<String, Integer> headerMap = parser.getHeaderMap();
        LOGGER.log(Level.INFO, "header map is {0}",headerMap);
        if(headerMap.size() != 3) {
            return false;
        }

        String[] validHeaders = {"Reg number","make","colour"};

        for(String key : headerMap.keySet()) {
            LOGGER.log(Level.INFO, "header key is {0}",key);

            if (!Arrays.stream(validHeaders).anyMatch(key::equalsIgnoreCase)) {
                return false;
            }
        }

        return true;
    }

    public boolean test(){
        return true;
    }

}
