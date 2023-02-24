package Core.Utilities;

import com.google.gson.Gson;;
import java.io.FileWriter;
import java.io.IOException;


import static Pages_Wiki.WikiPage.getListOfLinks;

public class JSONUtils {

    public static void writeToJsonFile(String testFile) throws IOException {
        Gson gson = new Gson();
        String arrayData = gson.toJson(getListOfLinks());
        FileWriter file = new FileWriter("src/test/resources/test-output/" + testFile);
        file.write(arrayData);
        file.close();
    }

}
