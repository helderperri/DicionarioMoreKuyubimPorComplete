package japiim.dic.morekuyubim.por;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseImagesAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetImagesTableValues;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterImagesExpanded;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterImagesIntroExpanded;

public class ViewAdapterImageIntro extends AppCompatActivity {

    int imageId;

    int position;
    ImageView rowImage;
    RecyclerView.LayoutManager layoutManager;



    RecyclerView rvImagesExpanded;
    RecyclerViewAdapterImagesIntroExpanded recyclerViewAdapterImagesIntroExpanded;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout_image_expanded);

        imageId = getIntent().getExtras().getInt("image_id");



        rvImagesExpanded = findViewById(R.id.rvImagesExpanded);
        rvImagesExpanded.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImagesExpanded.setLayoutManager(layoutManager);

        recyclerViewAdapterImagesIntroExpanded = new RecyclerViewAdapterImagesIntroExpanded(this, imageId, rvImagesExpanded);
        rvImagesExpanded.setAdapter(recyclerViewAdapterImagesIntroExpanded);


    }

    public void backToEntry (View view){

        finish();

    }
}
