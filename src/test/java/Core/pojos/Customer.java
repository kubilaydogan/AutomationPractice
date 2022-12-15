package Core.pojos;

import Core.Utilities.CucumberUtils;
import io.cucumber.datatable.DataTable;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Customer {
    private String email, password;

    public Customer(DataTable dataTable) {
        Map<String, String> data = CucumberUtils.tableConverter(dataTable);
        this.email = data.get("email");
        this.password = data.get("password");
    }

}
