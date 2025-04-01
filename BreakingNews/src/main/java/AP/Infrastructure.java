package AP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.*;

public class Infrastructure {

    private final String URL;
    private final String APIKEY;
    private final String JSONRESULT;
    private ArrayList<News> newsList;// TODO: Create the News class

    public Infrastructure(String APIKEY) {
        this.APIKEY = APIKEY;
        this.URL = "https://newsapi.org/v2/everything?q=tesla&from=" + LocalDate.now().minusDays(1) + "&sortBy=publishedAt&apiKey=";
        this.JSONRESULT = getInformation();
        parseInformation();
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    private String getInformation() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + APIKEY))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new IOException("HTTP error code: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("!!Exception : " + e.getMessage());
        }
        return null;
    }

    private void parseInformation() {

        String json = getInformation();
        newsList = new ArrayList<>();
        JSONObject obj = new JSONObject(json);
        JSONArray newsArray = obj.getJSONArray("articles");
        for(int i = 0; i < Math.min(20, newsArray.length()); i++) {
            JSONObject newsobj = newsArray.getJSONObject(i);
            News news = new News();
            news.setPublishedAt(newsobj.optString("publishedAt", "No date"));
            news.setAuthor(newsobj.optString("author", "Unknown"));
            news.setURL(newsobj.optString("url", ""));
            news.setDescription(newsobj.optString("description", "No description available"));
            news.setSourceName(newsobj.optJSONObject("source").optString("name", "Unknown Source"));
            news.setTitle(newsobj.optString("title", "No title"));

            newsList.add(news);
        }


    }

    public void displayNewsList() {
        System.out.println("-----------------------------------------------NEWS------------------------------------------");
        for(int i = 0;i < newsList.size();i++){
            int number = i ;
            News news = newsList.get(i);
            System.out.println(  ++number + "->"+ news.getTitle());
        }

        System.out.println("\n"+"Choose a New from Listnews");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        if(choice <= 0 || choice > 20){
            System.out.println("PLEASE ENTER A NUMBER BETWEEN 1 AND 20");
        }

        News neww = newsList.get(choice - 1);
        System.out.println("_________________________________________________");
        neww.displaynew();
        System.out.println("_________________________________________________");
        System.out.println("Prees R to return");
        char ch = in.next().charAt(0);

        if(ch == 'r' || ch == 'R'){
            displayNewsList();
        }
    }
}