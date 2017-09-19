package lamaatech.com.booklistingapp.Networking;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import lamaatech.com.booklistingapp.Book;
import lamaatech.com.booklistingapp.MainActivity.Model.MainContract;
import lamaatech.com.booklistingapp.Parsing.BookParser;
import lamaatech.com.booklistingapp.R;

/**
 * Created by MrHacker on 9/19/2017.
 */

public class BookAsyncTask extends AsyncTask<String, Void, ArrayList<Book>> {

    private MainContract.IPostResult postResult;
    private static final String TAG = BookAsyncTask.class.getSimpleName();
    @BindView(R.id.progressBar)
    protected ProgressBar progressBar;
    private Activity activity;

    public BookAsyncTask(MainContract.IPostResult newPostResult, Activity newActivity) {
        postResult = newPostResult;
        activity = newActivity;
        progressBar = (ProgressBar) activity.findViewById(R.id.progressBar);
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressBar.getVisibility() != View.VISIBLE)
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList<Book> doInBackground(String... urls) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String BookJsonStr = null;

        try {

            URL url = new URL(urls[0]);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(String.valueOf(line + "\n"));
            }

            if (buffer.length() == 0) {
                return null;
            }
            BookJsonStr = buffer.toString();

        } catch (IOException e) {
            Log.e(TAG, "Error ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }
        return BookParser.parseJsonToBookList(BookJsonStr);
    }

    @Override
    protected void onPostExecute(ArrayList<Book> books) {
        super.onPostExecute(books);
        postResult.onPostResult(books);
        progressBar.setVisibility(View.GONE);
    }
}
