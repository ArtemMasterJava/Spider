import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

/**
 * Created by USER on 05.10.2015.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        System.out.print("Input url : ");
        String url1 = scn.nextLine().trim();

        Document document = Jsoup.connect(url1).get();
        print("Fetching %s...",url1);
        Elements links = document.select("a[href]");


        UrlTester urlTester;
        try{
            urlTester = new UrlTester(url1);
            urlTester.connect();
            int code = urlTester.getStatus();
            if(code < 0){
                System.out.println("error");
            }
            else if (code < 200){
                System.out.println("info");
            }
            else if (code < 300){
                System.out.println("success");
            }
            else if (code < 400){
                System.out.println("redirection");
            }
            else if (code < 500){
                System.out.println("client error");
            }
            else {
                System.out.println("server error");
            }
        }
        catch (MalformedURLException e) {
            System.err.println("Incorrect format of URL");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error during connection");
            System.err.println(e.getMessage());
            System.exit(2);
        }


        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }

        private static String trim(String s, int width) {
            if (s.length() > width)
                return s.substring(0, width-1) + ".";
            else
                return s;
        }

         private static void print(String msg, Object... args) {
              System.out.println(String.format(msg, args));
    }



       /* Scanner scn = new Scanner(System.in);
        System.out.print("Input URL ");
        String url = scn.nextLine().trim();
        UrlTester urlTester;
        try {
            urlTester = new UrlTester(url);
            urlTester.connect();
            int code = urlTester.getStatus();
            if(code < 0){
                System.out.println("error");
            }
            else if (code < 200){
                System.out.println("info");
            }
            else if (code < 300){
                System.out.println("success");
            }
            else if (code < 400){
                System.out.println("redirection");
            }
            else if (code < 500){
                System.out.println("client error");
            }
            else {
                System.out.println("server error");
            }
        } catch (MalformedURLException e) {
            System.err.println("Incorrect format of URL");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error during connection");
            System.err.println(e.getMessage());
            System.exit(2);
        }
        */
    }

