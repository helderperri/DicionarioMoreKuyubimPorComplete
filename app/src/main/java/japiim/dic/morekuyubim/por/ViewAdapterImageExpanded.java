package japiim.dic.morekuyubim.por;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseImagesAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetImagesTableValues;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterImagesExpanded;

public class ViewAdapterImageExpanded extends AppCompatActivity {

    long imageId;

    long senseBundleId;
    int position;

    RecyclerView.LayoutManager layoutManager;


    List<GetImagesTableValues> imageList = new ArrayList<>();

    DatabaseImagesAdapter databaseImagesAdapter;

    RecyclerView rvImagesExpanded;
    RecyclerViewAdapterImagesExpanded recyclerViewAdapterImagesExpanded;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout_image_expanded);
        imageId = getIntent().getExtras().getLong("image_id");

        senseBundleId = getIntent().getExtras().getLong("sense_bundle_id");

        position = getIntent().getExtras().getInt("position");


        databaseImagesAdapter = new DatabaseImagesAdapter(this, senseBundleId, 0);

        imageList = databaseImagesAdapter.getImages();

        rvImagesExpanded = findViewById(R.id.rvImagesExpanded);
        rvImagesExpanded.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        //LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImagesExpanded.setLayoutManager(layoutManager);

        recyclerViewAdapterImagesExpanded = new RecyclerViewAdapterImagesExpanded(this, imageList, rvImagesExpanded);
        rvImagesExpanded.setAdapter(recyclerViewAdapterImagesExpanded);
        rvImagesExpanded.scrollToPosition(position);


    }

    public void backToEntry (View view){

        finish();

    }
}
