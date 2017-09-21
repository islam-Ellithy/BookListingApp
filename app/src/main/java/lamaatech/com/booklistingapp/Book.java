package lamaatech.com.booklistingapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by MrHacker on 9/19/2017.
 */

public class Book implements Serializable {
    private String title;
    private String author;

    public Book() {
    }

    public Book(JSONObject object) {
        try {
            if (object.has("title"))
                title = object.getString("title");
            if (object.has("authors"))
                author = object.getJSONArray("authors").getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
