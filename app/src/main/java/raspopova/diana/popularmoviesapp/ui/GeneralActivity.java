package raspopova.diana.popularmoviesapp.ui;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import raspopova.diana.popularmoviesapp.R;

/**
 * Created by Diana on 9/3/2016.
 */
public class GeneralActivity extends AppCompatActivity {

    protected void startActivity(Class activityClass) {
        startActivity(activityClass, false);
    }


    protected void startActivity(Class activityClass, boolean lockBackAction) {
        Intent intent = new Intent(this, activityClass);
        if (lockBackAction) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }

        startActivity(intent);
    }

    public void showErrorSnack(final String error, CoordinatorLayout coordinatorLayout) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, error, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.error_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        // Change background color
        snackbar.getView().setBackgroundColor(getResources().getColor(R.color.white));
        // Changing action button text color
        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));

        // Changing message text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        textView.setMaxLines(4);
        snackbar.show();
    }
}
