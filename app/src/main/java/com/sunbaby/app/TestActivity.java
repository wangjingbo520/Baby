package com.sunbaby.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.sunbaby.app.adapter.SingleCheckAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends Activity {

    @BindView(R.id.listview)
    ListView listview;

    private SingleCheckAdapter singleCheckAdapter;
    private List<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        singleCheckAdapter = new SingleCheckAdapter(this,strings);
        listview.setAdapter(singleCheckAdapter);

    }
}
