import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by USER on 12.10.2015.
 */
public class UrlSpider {
    private LinkedList<URL> links;
    private String domain;
    private HashSet<URL> visited;

    public UrlSpider(URL url){
        links = new LinkedList<URL>();
        links.add(url);
        domain = url.getHost();
        visited = new HashSet<URL>();
    }

    public URL getNext(){
        if(links.size()==0){
            return null;
        }
        return links.pop();
    }

    public void add(URL newUrl){
        if (domain.equals(newUrl.getHost())){
            links.add(newUrl);
        }
    }

    public boolean isEmpty(){
        return links.isEmpty();
    }

    public void step()throws IOException{
        URL urlDoc = getNext();
        Document document = Jsoup.connect(urlDoc.toString()).get();
        print("Fetching %s...",urlDoc.toString());
        Elements links = document.select("a[href]");

        for (Element elem : links) {
            String href = elem.attr("href");
            System.out.println(href);
            UrlTester urlTester;
            try {
                URL c = new URL(urlDoc, href);
                if(visited.contains(c)){
                    continue;
                }else {
                    visited.add(c);
                }
                urlTester = new UrlTester(c);
                urlTester.connect();
                int code = urlTester.getStatus();
                if (code < 0) {
                    System.out.println("error");
                } else if (code < 200) {
                    System.out.println("info");
                } else if (code < 300) {
                    if (code == 200){
                        add(c);
                    }
                    System.out.println("success");
                } else if (code < 400) {
                    System.out.println("redirection");
                } else if (code < 500) {
                    System.out.println("client error");
                } else {
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

    }


