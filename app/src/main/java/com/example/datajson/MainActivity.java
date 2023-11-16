package com.example.datajson;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FetchDataTask.OnDataFetchedListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Thay đổi đường dẫn URL để phản ánh nguồn dữ liệu của bạn
        String url = "https://dummyjson.com/quotes";

        FetchDataTask fetchDataTask = new FetchDataTask(this);
        fetchDataTask.execute(url);
    }

    @Override
    public void onDataFetched(List<Quote> items) {
        MyAdapter adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);
    }
}
