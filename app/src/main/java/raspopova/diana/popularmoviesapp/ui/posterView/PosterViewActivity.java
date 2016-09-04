package raspopova.diana.popularmoviesapp.ui.posterView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import raspopova.diana.popularmoviesapp.ui.GeneralActivity;
import raspopova.diana.popularmoviesapp.R;

/**
 * Created by Diana on 9/3/2016.
 */
public class PosterViewActivity extends GeneralActivity {

    @BindView(R.id.imagePoster)
    ImageView posterImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);
        ButterKnife.bind(this);

        if(getIntent().getExtras()!=null){
            String posterUrl = getIntent().getStringExtra("poster");
            Glide.with(this).load(posterUrl).into(posterImage);
        }
    }
}
