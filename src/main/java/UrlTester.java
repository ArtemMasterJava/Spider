

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by USER on 05.10.2015.
 */
public class UrlTester {
    private URL url;
    private HttpURLConnection http;
    private int code;

    public UrlTester(String url) throws MalformedURLException{
        this.url = new URL(url);
    }

    public UrlTester(URL url){
        this.url = url;
    }


    public void connect() throws IOException {
        http =(HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setReadTimeout(10000);
        http.connect();
    }

    public int getStatus() {
        if (http==null){
            return code=-2;
        }
        try {
            return code = http.getResponseCode();
        } catch (IOException e) {
            return code=-3;
        }
    }
}
