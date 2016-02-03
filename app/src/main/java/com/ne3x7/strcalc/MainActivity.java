package com.ne3x7.strcalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MENU_SETTINGS = 4;
    public static final int MENU_QUIT = 5;
    public static final String LAST_EXPRESSION = "last expression";
    public static final String LAST_RESULT = "last result";

    private EditText expr;
    private Button use;
    private Button reset;
    private TextView res;
    private SharedPreferences pref;

    public int index = 0;
    private double latest = 0.0;
    private boolean show = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expr = (EditText) findViewById(R.id.expr);
        Button button = (Button) findViewById(R.id.button);
        res = (TextView) findViewById(R.id.res);
        use = (Button) findViewById(R.id.use);
        reset = (Button) findViewById(R.id.reset);

        button.setOnClickListener(this);
        use.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            show = pref.getBoolean("save last", false);
        } catch (Exception e) {

        }

        pref = getSharedPreferences("prefs", MODE_PRIVATE);

        try {
            if (!pref.getBoolean("show reset btn", true))
                reset.setVisibility(View.INVISIBLE);

            if (!pref.getBoolean("show use btn", true))
                use.setVisibility(View.INVISIBLE);
        } catch (Exception e) {

        }

        if (show)
            this.load();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (show)
            this.save();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.button):
                String str = expr.getText().toString();

                StringBuffer s = new StringBuffer("");

                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) != ' ')
                        s.append(str.charAt(i));
                }

                str = s.toString();

                if (TextUtils.isEmpty(str)) {
                    res.setText(R.string.noinput);
                    return;
                }

                Calculator calc = new Calculator(str);
                latest = calc.calculate();

                if (!calc.isOkay()) {
                    res.setText(R.string.wrongexpr);
                } else if (res.getText().toString().equals(R.string.res)) {
                    res.append(" " + Double.toString(latest));
                } else {
                    res.setText("Result: " + Double.toString(latest));
                }

                index = 0;

                reset.setEnabled(true);
                use.setEnabled(true);

                break;
            case (R.id.use):
                expr.setText(Double.toString(latest));
                res.setText(R.string.res);
                index = 0;
                use.setEnabled(false);
                break;
            case (R.id.reset):
                expr.setText("");
                res.setText(R.string.res);
                use.setEnabled(false);
                reset.setEnabled(false);
                index = 0;
                latest = 0;
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_SETTINGS, 1, R.string.settings);
        menu.add(0, MENU_QUIT, 1, R.string.quit);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SETTINGS:
                Intent intent = new Intent(this, SettingsPanel.class);
                startActivity(intent);
                break;
            case MENU_QUIT:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void save() {
        pref = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LAST_EXPRESSION, expr.getText().toString());
        editor.putFloat(LAST_RESULT, (float) latest);
        editor.commit();
        Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
    }

    private void load() {
        pref = getSharedPreferences("prefs", MODE_PRIVATE);
        String rcvExpr = pref.getString(LAST_EXPRESSION, "");
        Float rcvRes = pref.getFloat(LAST_RESULT, 0);
        expr.setText(rcvExpr);
        latest = Double.valueOf(rcvRes);
        res.setText("Result: " + Float.toString(rcvRes));
        use.setEnabled(true);
        reset.setEnabled(true);
        Toast.makeText(MainActivity.this, "Loaded", Toast.LENGTH_SHORT).show();
    }
}
