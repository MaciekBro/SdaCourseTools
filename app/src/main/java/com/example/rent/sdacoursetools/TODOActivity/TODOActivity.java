package com.example.rent.sdacoursetools.TODOActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rent.sdacoursetools.R;

import java.util.ArrayList;

/**
 * Created by RENT on 2017-02-22.
 */

public class TODOActivity extends AppCompatActivity implements OnItemCheckStateChanged {

    private static final String ADAPTER_DATA = "adapter_data";
    private TODOListAdapter todoListAdapter;
    private String activityTitle;
    private ActionMode actionMode;


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
                if (editText.length() != 0)
                    todoListAdapter.addItem(editText.getText().toString());
                editText.setText("");
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
        if (item.getItemId() == R.id.action_delete) {
            todoListAdapter.deleteAllCheckItems();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemCheckStateChanged(int checkedItemsCount) {
        if (checkedItemsCount > 0) {

            if (actionMode == null) {
                createActionMode();  //stworzenie innego action Bar po zaznaczeniu checkboxa!
            }
            actionMode.setTitle(getResources().getQuantityString(R.plurals.checked_items_plural, checkedItemsCount, checkedItemsCount)); //trzeba dwa razy, najpierw mowisz co cie interesuje potem podajesz wartosc

        } else {
            if (actionMode != null) {
                actionMode.finish();
            }
            getSupportActionBar().setTitle(activityTitle);
        }
    }

    private void createActionMode() {
        actionMode = startSupportActionMode(new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.todo_action_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {        //w momencie jak sie pojawia

                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {   //do rozpoznawania ktory item zostal klikniety
                if (item.getItemId() == R.id.action_delete) {
                    todoListAdapter.deleteAllCheckItems();
                    return true;        //jesli cos obslugujemy w callbackach  to zwracamy TRUE
                }
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                todoListAdapter.deselectAllItems();
                actionMode = null;

            }
        });

        ;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(ADAPTER_DATA, new ArrayList<>(todoListAdapter.getItems()));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        todoListAdapter.setItems(savedInstanceState.<TODOListItem>getParcelableArrayList(ADAPTER_DATA));
        onItemCheckStateChanged(todoListAdapter.getCheckedItemsCount());

    }
}
