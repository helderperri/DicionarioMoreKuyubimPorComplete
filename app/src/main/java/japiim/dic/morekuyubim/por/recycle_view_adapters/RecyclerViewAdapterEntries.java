package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseFormBundlesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseSenseBundlesAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetFormBundlesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSenseBundlesTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterEntries extends RecyclerView.Adapter<RecyclerViewAdapterEntries.ViewHolder> {

    Context context;
    List<GetEntriesTableValues> entriesList;
    RecyclerView rvEntries;

    ArrayList<MediaPlayer> mediaPlayerArray;
    RecyclerView.LayoutManager layoutManager;

    public static MediaPlayer entryMediaPlayer;
    public static ArrayList<ImageView> sViews = new ArrayList<ImageView>();

    DatabaseFormBundlesAdapter databaseFormBundlesAdapter;
    RecyclerView rvFormBundles;
    RecyclerViewAdapterFormBundles recyclerViewAdapterFormBundles;
    List<GetFormBundlesTableValues> formBundlesList = new ArrayList<>();


    DatabaseSenseBundlesAdapter databaseSenseBundlesAdapter;
    RecyclerView rvSenseBundles;
    RecyclerViewAdapterSenseBundles recyclerViewAdapterSenseBundles;
    List<GetSenseBundlesTableValues> senseBundlesList = new ArrayList<>();


    long entryId;

    public static class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public RecyclerViewAdapterEntries(Context context, List<GetEntriesTableValues> entriesList, RecyclerView rvEntries){
        this.context = context;
        this.entriesList = entriesList;
        this.rvEntries = rvEntries;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterEntries.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_entry, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterEntries.ViewHolder viewHolder, int i) {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            Log.d("myz", ""+SDK_INT);
            if (!Environment.isExternalStorageManager()) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);//permission request code is just an int
            }
        }else {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

        GetEntriesTableValues getEntriesTableValues = entriesList.get(i);
        //viewHolder.rowEntryId.setText("entry id: "+getEntriesTableValues.getEntryId());

        entryId = getEntriesTableValues.getEntryId();

        /*
        now I am trying to get the data from the tables "form_bundles" and "sense_bundles" and
        populate it on the Recycle Views "rvFormBundles" (1) and "rvSenseBundles" (2).
        Both "form_bundles" and "sense_bundles" tables are child of "entries" table.
        */

        /* 1 populating rvFormBundles*/
        databaseFormBundlesAdapter = new DatabaseFormBundlesAdapter(context, entryId, 0);
        formBundlesList = databaseFormBundlesAdapter.getFormBundles();
        rvFormBundles = viewHolder.itemView.findViewById(R.id.rvFormBundles);
        rvFormBundles.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvFormBundles.setLayoutManager(layoutManager);
        recyclerViewAdapterFormBundles = new RecyclerViewAdapterFormBundles(context, formBundlesList, rvFormBundles);
        rvFormBundles.setAdapter(recyclerViewAdapterFormBundles);

        /* 2 populating rvSenseBundles*/
        databaseSenseBundlesAdapter = new DatabaseSenseBundlesAdapter(context, entryId, 0);
        senseBundlesList = databaseSenseBundlesAdapter.getSenseBundles();
        rvSenseBundles = viewHolder.itemView.findViewById(R.id.rvSenseBundles);
        rvSenseBundles.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvSenseBundles.setLayoutManager(layoutManager);
        recyclerViewAdapterSenseBundles = new RecyclerViewAdapterSenseBundles(context, senseBundlesList, rvSenseBundles);
        rvSenseBundles.setAdapter(recyclerViewAdapterSenseBundles);

    }


    public void onDestroy() {
        // TODO Auto-generated method stub
        /* Destroy all child reclyceViewAdapters */
        recyclerViewAdapterFormBundles.onDestroy();
        recyclerViewAdapterSenseBundles.onDestroy();

        Log.d("Main", " GNM-JPP ON DESTROY ENTRIES ADAPTER");

    }
    @Override
    public int getItemCount() {
        return entriesList.size();
    }

}
