package com.ne3x7.strcalc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NumpadFragment extends Fragment implements View.OnClickListener {

    EditText expr;
    Button bOne;
    Button bTwo;
    Button bThree;
    Button bFour;
    Button bFive;
    Button bSix;
    Button bSeven;
    Button bEight;
    Button bNine;
    Button bZero;
    Button bPoint;
    Button bTogglePm;
    Button bPlus;
    Button bMinus;
    Button bMul;
    Button bDiv;
    Button bLeft;
    Button bRight;
    Button bPwr;
    Button bClear;
    SharedPreferences pref;

    boolean show = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_numpad_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        expr = (EditText) getActivity().findViewById(R.id.expr);

        bOne = (Button) getActivity().findViewById(R.id.bOne);
        bTwo = (Button) getActivity().findViewById(R.id.bTwo);
        bThree = (Button) getActivity().findViewById(R.id.bThree);
        bFour = (Button) getActivity().findViewById(R.id.bFour);
        bFive = (Button) getActivity().findViewById(R.id.bFive);
        bSix = (Button) getActivity().findViewById(R.id.bSix);
        bSeven = (Button) getActivity().findViewById(R.id.bSeven);
        bEight = (Button) getActivity().findViewById(R.id.bEight);
        bNine = (Button) getActivity().findViewById(R.id.bNine);
        bZero = (Button) getActivity().findViewById(R.id.bZero);
        bPoint = (Button) getActivity().findViewById(R.id.bPoint);
        bTogglePm = (Button) getActivity().findViewById(R.id.bTogglePm);
        bPlus = (Button) getActivity().findViewById(R.id.bPlus);
        bMinus = (Button) getActivity().findViewById(R.id.bMinus);
        bMul = (Button) getActivity().findViewById(R.id.bMul);
        bDiv = (Button) getActivity().findViewById(R.id.bDiv);
        bLeft = (Button) getActivity().findViewById(R.id.bLeftPrt);
        bRight = (Button) getActivity().findViewById(R.id.bRightPrt);
        bPwr = (Button) getActivity().findViewById(R.id.bPwr);
        bClear = (Button) getActivity().findViewById(R.id.bClear);

        bOne.setOnClickListener(this);
        bTwo.setOnClickListener(this);
        bThree.setOnClickListener(this);
        bFive.setOnClickListener(this);
        bFour.setOnClickListener(this);
        bSix.setOnClickListener(this);
        bSeven.setOnClickListener(this);
        bEight.setOnClickListener(this);
        bNine.setOnClickListener(this);
        bZero.setOnClickListener(this);
        bPoint.setOnClickListener(this);
        bTogglePm.setOnClickListener(this);
        bPlus.setOnClickListener(this);
        bMinus.setOnClickListener(this);
        bMul.setOnClickListener(this);
        bDiv.setOnClickListener(this);
        bLeft.setOnClickListener(this);
        bRight.setOnClickListener(this);
        bPwr.setOnClickListener(this);
        bClear.setOnClickListener(this);

        pref = getActivity().getSharedPreferences("prefs", Activity.MODE_PRIVATE);

        try {
            show = pref.getBoolean("show numpad", true);
        } catch (Exception e) {

        }

        if (!show) {
            bOne.setVisibility(View.INVISIBLE);
            bTwo.setVisibility(View.INVISIBLE);
            bThree.setVisibility(View.INVISIBLE);
            bFour.setVisibility(View.INVISIBLE);
            bFive.setVisibility(View.INVISIBLE);
            bSix.setVisibility(View.INVISIBLE);
            bSeven.setVisibility(View.INVISIBLE);
            bEight.setVisibility(View.INVISIBLE);
            bNine.setVisibility(View.INVISIBLE);
            bZero.setVisibility(View.INVISIBLE);
            bPoint.setVisibility(View.INVISIBLE);
            bTogglePm.setVisibility(View.INVISIBLE);
            bPlus.setVisibility(View.INVISIBLE);
            bMinus.setVisibility(View.INVISIBLE);
            bMul.setVisibility(View.INVISIBLE);
            bDiv.setVisibility(View.INVISIBLE);
            bLeft.setVisibility(View.INVISIBLE);
            bRight.setVisibility(View.INVISIBLE);
            bPwr.setVisibility(View.INVISIBLE);
            bClear.setVisibility(View.INVISIBLE);
        }
    }

    public void onClick (View view) {
        String expression = expr.getText().toString();

        switch (view.getId()) {
            case R.id.bOne:
                expr.append("1");
                break;
            case R.id.bTwo:
                expr.append("2");
                break;
            case R.id.bThree:
                expr.append("3");
                break;
            case R.id.bFour:
                expr.append("4");
                break;
            case R.id.bFive:
                expr.append("5");
                break;
            case R.id.bSix:
                expr.append("6");
                break;
            case R.id.bSeven:
                expr.append("7");
                break;
            case R.id.bEight:
                expr.append("8");
                break;
            case R.id.bNine:
                expr.append("9");
                break;
            case R.id.bZero:
                expr.append("0");
                break;
            case R.id.bPoint:
                expr.append(".");
                break;
            case R.id.bTogglePm:
                String e = "";

                if (!expression.isEmpty()) {
                    if (expression.charAt(0) == '-') {
                        if (expression.length() > 1) {
                            e = new StringBuffer(expression).substring(1, expression.length() - 1);
                        } else if (expression.length() == 1) {
                            e = String.valueOf(expression.charAt(0));
                        }
                        expr.append(e);
                    } else {
                        expr.append("-" + expression);
                    }
                } else {
                    expr.append("-");
                }
                break;
            case R.id.bPlus:
                expr.append("+");
                break;
            case R.id.bMinus:
                expr.append("-");
                break;
            case R.id.bMul:
                expr.append("*");
                break;
            case R.id.bDiv:
                expr.append("/");
                break;
            case R.id.bLeftPrt:
                expr.append("(");
                break;
            case R.id.bRightPrt:
                expr.append(")");
                break;
            case R.id.bPwr:
                expr.append("^");
                break;
            case R.id.bClear:
                expr.setText("");
                break;
        }
    }
}
