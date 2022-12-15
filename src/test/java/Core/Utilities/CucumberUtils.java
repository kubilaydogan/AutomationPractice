package Core.Utilities;

import io.cucumber.datatable.DataTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CucumberUtils {

    public static synchronized Map<String, String> tableConverter(DataTable dataTable) {
        List<List<String>> data = dataTable.asLists(String.class);
        Map<String, String> mapTable = new HashMap<>();
        for (List<String> rows : data) {
            mapTable.put(rows.get(0), rows.get(1));
        }
        return mapTable;
    }

}
