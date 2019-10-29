package sample;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Download {
    //pobieranie jsonow
    String line = null;

    public String download(String adress) throws IOException {
        try (InputStream in = URI.create(adress).toURL().openStream()) {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
    }


    public String curl() {
        try {

            String url = "https://airapi.airly.eu/v2/installations/204";

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            conn.setRequestMethod("GET");

            String userpass = "user" + ":" + "uBMumGngCmz0irrNfFbbim12ZbQKRpLU";
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
            conn.setRequestProperty("Authorization", basicAuth);

            String data = "{\"format\":\"json\",\"pattern\":\"#\"}";
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(data);
            out.close();

            new InputStreamReader(conn.getInputStream());

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
