package lamaatech.com.booklistingapp.MainActivity.Controller;

import android.app.Activity;

import java.util.ArrayList;

import lamaatech.com.booklistingapp.Book;
import lamaatech.com.booklistingapp.MainActivity.Model.MainContract;
import lamaatech.com.booklistingapp.MainActivity.Model.MainModel;

/**
 * Created by MrHacker on 9/19/2017.
 */

public class MainController implements MainContract.IController {
    private MainModel model;
    private MainContract.IView view;
    private Activity activity;

    public MainController(MainContract.IView newView, Activity newActivity) {
        view = newView;
        activity = newActivity;
        model = new MainModel(this,newActivity);
    }

    @Override
    public void requestBookListFromServer(Integer maxResults, String topic) {
        model.requestBookList(maxResults, topic);
    }

    @Override
    public void updateBookList(ArrayList<Book> books) {
        view.updateBookList(books);
    }
}
