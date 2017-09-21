package lamaatech.com.booklistingapp.Parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import lamaatech.com.booklistingapp.Book;

/**
 * Created by MrHacker on 9/19/2017.
 */

public class BookParser {
    public static ArrayList<Book> parseJsonToBookList(String jsonStr) {

        ArrayList<Book> booklist = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonArray;
            if (jsonObject.has("items")) {
                jsonArray = jsonObject.getJSONArray("items");

                Book newBook;
                for (int i = 0; i < jsonArray.length(); i++) {
                    if (jsonArray.getJSONObject(i).has("volumeInfo")) {
                        newBook = new Book(jsonArray.getJSONObject(i).getJSONObject("volumeInfo"));
                        booklist.add(newBook);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return booklist;
    }
}
