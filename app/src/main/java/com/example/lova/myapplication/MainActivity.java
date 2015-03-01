package com.example.lova.myapplication;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    double temp;

    public void numBtnPressed(View view) {
        TextView tView = (TextView) findViewById(R.id.textView);
        Button btn = (Button) view;

        String buttonText = btn.getText().toString();
        tView.setText(tView.getText() + buttonText);
    }

    public void dotBtnPressed(View view) {
        TextView tView = (TextView) findViewById(R.id.textView);

        String currentString = tView.getText().toString();
        if (currentString.indexOf(".") > 0) {
            return;
        } else {
            tView.setText(tView.getText() + ".");
        }

    }

    public void calBtnPressed(View view) {
        TextView tView = (TextView) findViewById(R.id.textView);
        TextView tView2 = (TextView) findViewById(R.id.textView2);

        Button btn = (Button) view;
        String buttonText = btn.getText().toString();

        tView2.setText(buttonText);

        temp = Double.parseDouble(tView.getText().toString());

        tView.setText("");
    }

    public void equalBtnPressed(View view) {
        TextView tView = (TextView) findViewById(R.id.textView);
        TextView tView2 = (TextView) findViewById(R.id.textView2);

        Button btn = (Button) view;

//        if (tView2.getText().toString() == "+"){
//
//        }

        switch (tView2.getText().toString()) {
            case "+":
                double sum = temp + Double.parseDouble(tView.getText().toString());
                tView.setText(String.valueOf(sum));
                break;
            case "-":
                sum = temp - Double.parseDouble(tView.getText().toString());
                tView.setText(String.valueOf(sum));
                break;
            case "*":
                sum = temp * Double.parseDouble(tView.getText().toString());
                tView.setText(String.valueOf(sum));
                break;
            case "/":
                sum = temp / Double.parseDouble(tView.getText().toString());
                tView.setText(String.valueOf(sum));
                break;
        }

    }

    public void cleanBtnPressed(View view) {
        TextView tView = (TextView) findViewById(R.id.textView);
        TextView tView2 = (TextView) findViewById(R.id.textView2);

        tView.setText("");
        tView2.setText("");
        temp = 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
