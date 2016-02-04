import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chetankokil on 2/4/16.
 */
@Slf4j
public class RestUtil {

    public static String get(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        StringBuilder sb = new StringBuilder();
        try {
            log.debug("Url is {}", url);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            byte[] buffer = new byte[1024];
            if (entity != null) {
                log.debug("Entity is not null");
                InputStream inputStream = entity.getContent();
                try {
                    int bytesRead = 0;
                    BufferedInputStream bis = new BufferedInputStream(inputStream);

                    while ((bytesRead = bis.read(buffer)) != -1) {
                        String chunk = new String(buffer, 0, bytesRead);
                        sb.append(chunk);
                    }

                } catch (IOException ioException) {
                    // In case of an IOException the connection will be released
                    // back to the connection manager automatically
                    ioException.printStackTrace();
                } catch (RuntimeException runtimeException) {
                    // In case of an unexpected exception you may want to abort
                    // the HTTP request in order to shut down the underlying
                    // connection immediately.
                    httpGet.abort();
                    runtimeException.printStackTrace();
                } finally {
                    // Closing the input stream will trigger connection release
                    try {
                        inputStream.close();
                    } catch (Exception ignore) {
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return sb.length() > 0 ? sb.toString() : null;
    }
}
