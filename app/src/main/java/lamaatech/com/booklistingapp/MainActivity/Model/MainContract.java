package lamaatech.com.booklistingapp.MainActivity.Model;

import java.util.ArrayList;

import lamaatech.com.booklistingapp.Book;

/**
 * Created by MrHacker on 9/19/2017.
 */

public interface MainContract {
    interface IController {
        void requestBookListFromServer(Integer maxResults, String topic);

        void updateBookList(ArrayList<Book> books);
    }

    interface IView {
        void updateBookList(ArrayList<Book> books);

        void onClickSearchButton();
    }

    interface IModel {
        void requestBookList(Integer maxResults, String topic);
    }

    interface IPostResult {
        void onPostResult(ArrayList<Book> books);
    }
}
