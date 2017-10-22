package selenium.tests.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by anandbarhate on 22/10/2017.
 */
public class DataUtil {

    public JsonArray retrieveVehicleData() throws Exception {
        InputStream is = new URL("http://localhost:8080/filedata").openStream();
        JsonArray jsonElements = null;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);

            jsonElements = new Gson().fromJson(jsonText, JsonArray.class);
            /*jsonElements.forEach(
                    element -> {
                        JsonObject jobject = element.getAsJsonObject();
                        System.out.println(jobject.get("regNumber"));
                    }
            );*/


        } finally {
            is.close();
        }
        return jsonElements;

    }

    private  String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
