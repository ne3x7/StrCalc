package com.ne3x7.strcalc;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsPanel extends AppCompatActivity {

    public static final String[] PREFS_NAMES = {"save last", "show numpad", "show use btn", "show reset btn"};
    public static final int MENU_SAVE = 1;

    ListView preferences;
    SharedPreferences pref;
    SparseBooleanArray checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_panel);

        preferences = (ListView) findViewById(R.id.preferences);
        preferences.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.items,
                android.R.layout.simple_list_item_multiple_choice);

        preferences.setAdapter(adapter);

        for (int i = 0; i < PREFS_NAMES.length; i++)
            if (load(PREFS_NAMES[i]))
                preferences.setItemChecked(i, true);
    }

    @Override
    protected void onPause() {
        pref = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        checked = preferences.getCheckedItemPositions();

        for (int i = 0; i < PREFS_NAMES.length; i++) {
            editor.putBoolean(PREFS_NAMES[i], false);
            editor.commit();
        }

        for (int i = 0; i < checked.size(); i++) {
            int key = checked.keyAt(i);
            editor.putBoolean(PREFS_NAMES[key], checked.get(key));
            editor.commit();
        }

        Toast.makeText(SettingsPanel.this, "Settings saved", Toast.LENGTH_SHORT).show();

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_SAVE, 1, R.string.save);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        pref = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        checked = preferences.getCheckedItemPositions();

        for (int i = 0; i < PREFS_NAMES.length; i++) {
            editor.putBoolean(PREFS_NAMES[i], false);
            editor.commit();
        }

        for (int i = 0; i < checked.size(); i++) {
            int key = checked.keyAt(i);
            editor.putBoolean(PREFS_NAMES[key], checked.get(key));
            editor.commit();
        }

        Toast.makeText(SettingsPanel.this, "Settings saved", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    private boolean load(String str) {
        pref = getSharedPreferences("prefs", MODE_PRIVATE);
        try {
            return pref.getBoolean(str, false);
        } catch (Exception e) {
            return false;
        }
    }
}