import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

/**
 * Created by USER on 05.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
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
    }
}
