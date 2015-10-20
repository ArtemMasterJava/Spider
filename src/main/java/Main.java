import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by USER on 05.10.2015.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        // System.out.print("Input url : ");
        //String url1 = scn.nextLine().trim();
        String url1 = "http://www.almost-university.com/";
        UrlSpider spider = new UrlSpider(new URL(url1));
        while (!spider.isEmpty()){
            try {
                spider.step();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }

    }
}

