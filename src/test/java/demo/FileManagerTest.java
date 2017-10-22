package demo;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by anandbarhate on 21/10/2017.
 */
public class FileManagerTest {
    FileManager test = mock(FileManager.class);
    List<FileStructure> files;
    @Before
    public void setup() {
        files = new ArrayList<FileStructure>();
        files.add(new FileStructure("name","type",123,"extension", anyObject()));


    }

    @Test
    public void retrieveFiles() throws Exception {

        //List<FileStructure> fileList = test.retrieveFiles();
        when(test.retrieveFiles()).thenReturn(files);

        List<FileStructure> result = test.retrieveFiles();
        assertTrue(result.size() == 1);

    }

    @Test
    public void listSourceFiles() throws Exception {
    }

}