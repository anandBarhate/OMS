import java.io.File;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class File1 {

    public static void main(String[] args) {
        final String dir = ".";
        final String pattern = ".pdf";
        final File file = Paths.get(dir).toFile();
        
        Stream.of(file.list((pFile, pString) ->  pString.endsWith(pattern)))
                .forEach(System.out::println);;
    }
}