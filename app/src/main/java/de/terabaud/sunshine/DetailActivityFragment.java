package de.terabaud.sunshine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ShareActionProvider;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    public static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();

    private static final String FORECAST_HASHTAG = " #SunshineApp";

    private String mForeCastStr;


    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        ShareActionProvider shareActionProvider =
                (ShareActionProvider)MenuItemCompat.getActionProvider(menuItem);

        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.d(LOG_TAG, "Share action provider is null");
            Toast.makeText(getActivity(), "No sharing app installed.",Toast.LENGTH_SHORT).show();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.detail_text_view);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            mForeCastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            textView.setText(mForeCastStr);
        }
        return rootView;
    }

    private Intent createShareForecastIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET); (deprecated)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mForeCastStr + FORECAST_HASHTAG);
        return intent;
    }

}
