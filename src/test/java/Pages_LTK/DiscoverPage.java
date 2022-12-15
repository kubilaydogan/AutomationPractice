package Pages_LTK;

import Core.Driver;
import org.openqa.selenium.support.PageFactory;

public class DiscoverPage extends BasePage {

    public DiscoverPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


}
