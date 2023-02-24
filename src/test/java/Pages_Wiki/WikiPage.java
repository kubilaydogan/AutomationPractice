package Pages_Wiki;

import Pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WikiPage extends BasePage {
    public static List<String> getListOfLinks() {
        return listOfLinks;
    }

    private static List<String> listOfLinks = new ArrayList<>();
    private static Set<String> uniquelinks = new HashSet<>();

    public static void openPage(String link) {
        Assert.assertTrue("Link is not a valid wiki link", link.contains("wiki"));
        driver.get(link);
    }

    public static void getAllLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for(WebElement each : links){
            uniquelinks.add(each.getAttribute("href"));
        }
        listOfLinks.addAll(uniquelinks);
        links.clear();
        // listOfLinks.forEach(System.out::println);
        // System.out.println(listOfLinks.size());
    }

    public static void getAllLinksInTheList(int n) {
        for(int i = 0; i<n; i++){
            System.out.println("VISITED LINK: " + listOfLinks.get(i));
            openPage(listOfLinks.get(i));
            getAllLinks();
        }
        driver.close();
        //System.out.println(listOfLinks.size());
    }


}
