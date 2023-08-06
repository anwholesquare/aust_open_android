package com.khandokeranan.austopen;

import com.google.firebase.annotations.concurrent.Background;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class HtmlParserTask {

    private Executor executor = Executors.newSingleThreadExecutor();

    @Background
    public void parseHtmlFromUrl(String url, HtmlParserCallback callback) {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                // Make the HTTPS request
                Response response = client.newCall(request).execute();
                String htmlContent = response.body().string();

                // Use Jsoup to parse the HTML content

                return htmlContent;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });

        futureTask.run();

        // Execute the callback on the main thread when the task is completed
        executor.execute(() -> {
            try {
                String result = futureTask.get();
                if (result != null) {
                    callback.onSuccess(result);
                } else {
                    callback.onError();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
                callback.onError();
            }
        });
    }

    public interface HtmlParserCallback {
        void onSuccess(String result);
        void onError();
    }
}

