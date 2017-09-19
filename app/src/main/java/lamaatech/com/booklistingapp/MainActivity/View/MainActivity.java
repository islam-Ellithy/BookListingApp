package lamaatech.com.booklistingapp.MainActivity.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lamaatech.com.booklistingapp.Book;
import lamaatech.com.booklistingapp.MainActivity.Controller.MainController;
import lamaatech.com.booklistingapp.MainActivity.Model.MainContract;
import lamaatech.com.booklistingapp.R;

public class MainActivity extends AppCompatActivity implements MainContract.IView {

    private MainController controller;
    private BookListAdapter adapter;
    @BindView(R.id.listview)
    protected ListView listViewBooks;
    private ArrayList<Book> bookArrayList = null;
    @BindView(R.id.queryEditText)
    protected EditText queryEditText;
    private Integer position;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("list", bookArrayList);

        int position = listViewBooks.getVerticalScrollbarPosition();

        outState.putInt("position", position);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        controller = new MainController(this, this);

        boolean flag = false;
        if (savedInstanceState != null) {
            bookArrayList = (ArrayList<Book>) savedInstanceState.getSerializable("list");

            position = savedInstanceState.getInt("position");

            flag = true;
        } else {
            controller.requestBookListFromServer(20, "android");
        }

        adapter = new BookListAdapter(this, bookArrayList);

        listViewBooks.setAdapter(adapter);

        if (flag) {
            listViewBooks.smoothScrollToPosition(position);
        }

    }

    @Override
    public void updateBookList(ArrayList<Book> books) {
        bookArrayList = books;
        adapter.updateList(books);
    }

    @OnClick(R.id.searchButton)
    @Override
    public void onClickSearchButton() {
        if (queryEditText.length() > 0)
            controller.requestBookListFromServer(20, queryEditText.getText().toString());
    }

}
