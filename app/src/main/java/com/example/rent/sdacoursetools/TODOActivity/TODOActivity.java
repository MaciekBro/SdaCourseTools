package com.example.rent.sdacoursetools.TODOActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rent.sdacoursetools.R;

/**
 * Created by RENT on 2017-02-22.
 */

public class TODOActivity extends AppCompatActivity implements OnItemCheckStateChanged {

    private TODOListAdapter todoListAdapter;
    private String activityTitle;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_activity);
        activityTitle = getSupportActionBar().getTitle().toString();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        todoListAdapter = new TODOListAdapter();
        recyclerView.setAdapter(todoListAdapter);
        todoListAdapter.setCheckedListener(this);

        final EditText editText = (EditText) findViewById(R.id.todo_edit_text);

        Button addButton = (Button) findViewById(R.id.todo_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoListAdapter.addItem(editText.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.delete_item) {

            todoListAdapter.deleteAllCheckItems();

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemCheckStateChanged(int checkedItemsCount) {
        if(checkedItemsCount>0) {
            getSupportActionBar().setTitle("Checked items: " + checkedItemsCount);
        } else {
            getSupportActionBar().setTitle(activityTitle);
        }
    }
}
