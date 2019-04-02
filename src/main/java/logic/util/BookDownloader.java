package logic.util;

import com.google.gson.*;
import logic.domain.Library;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class BookDownloader {

    private BookDownloader() {}

    public static void getLibrary(String[] args) throws IOException {
        JsonObject jsonFile = getJsonFile(args[0]);
        if (args[1].equals("remote")) {
            String remoteURL = jsonFile.get("requestedUrl").getAsString();
            URLConnection request = connect(remoteURL);
            jsonFile = getJsonFile(request);
        }
        Library.get().add(JsonUtils.extractBooks(jsonFile));
    }

    private static JsonObject getJsonFile(URLConnection request) throws IOException {
        return new JsonParser().parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonObject();
    }

    private static JsonObject getJsonFile(String filepath) throws IOException {
        return new JsonParser().parse(new FileReader(filepath)).getAsJsonObject();
    }

    private static URLConnection connect(String url) throws IOException {
        URLConnection request = new URL(url).openConnection();
        request.connect();
        return request;
    }
}
