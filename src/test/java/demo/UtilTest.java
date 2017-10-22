package demo;

import org.apache.commons.csv.CSVParser;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by anandbarhate on 21/10/2017.
 */
public class UtilTest {
    private Util test = new Util();
    private CSVParser parser = mock(CSVParser.class);
    private Map<String, Integer> validHeaderMap;
    private Map<String, Integer> inValidHeaderMap;
    private Path path = mock(Path.class);
    private File file = mock(File.class);
    private Path absolutePath = mock(Path.class);

    @Before
    public void setUp() throws Exception {
        validHeaderMap = new HashMap<String, Integer>(Stream.of(
                new AbstractMap.SimpleEntry<>("Reg number", 0),
                new AbstractMap.SimpleEntry<>("make", 1),
                new AbstractMap.SimpleEntry<>("Colour", 2)
                )
                .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));


        inValidHeaderMap = new HashMap<String, Integer>(Stream.of(  new AbstractMap.SimpleEntry<>("blah", 0) ).collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

    }

    @Test
    public void testValidHeaders() throws Exception {
        when(parser.getHeaderMap()).thenReturn(validHeaderMap);
        assertTrue(test.validateFile(parser));
    }

    @Test
    public void testInValidHeaders() throws Exception {
        when(parser.getHeaderMap()).thenReturn(inValidHeaderMap);
        assertFalse(test.validateFile(parser));
    }


    @Test
    public void testReadFile() {
        when(path.toAbsolutePath()).thenReturn(absolutePath);
        when(path.toAbsolutePath().toFile()).thenReturn(file);
        test.readFile(path);
    }
}