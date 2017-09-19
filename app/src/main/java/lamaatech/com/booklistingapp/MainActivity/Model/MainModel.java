package lamaatech.com.booklistingapp.MainActivity.Model;

import android.app.Activity;

import java.util.ArrayList;

import lamaatech.com.booklistingapp.Book;
import lamaatech.com.booklistingapp.Networking.BookAsyncTask;

/**
 * Created by MrHacker on 9/19/2017.
 */

public class MainModel implements MainContract.IModel, MainContract.IPostResult {
    private final static String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    private MainContract.IController controller;
    private Activity activity;

    public MainModel(MainContract.IController newController, Activity newActivity) {
        controller = newController;
        activity = newActivity;
    }

    @Override
    public void requestBookList(Integer maxResults, String topic) {

        new BookAsyncTask(this,activity).execute(String.valueOf(baseUrl + topic + "&maxResults=" + maxResults));
    }

    @Override
    public void onPostResult(ArrayList<Book> books) {
        controller.updateBookList(books);
    }
}
