package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesVernacularAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesGlossesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesVernacularTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.ViewAdapterEntryBundles;

public class RecyclerViewAdapterEntriesScrollDisplayTl extends RecyclerView.Adapter<RecyclerViewAdapterEntriesScrollDisplayTl.ViewHolder> {

    Context context;
    List<GetEntriesGlossesTableValues> entriesGlossesList;
    RecyclerView rvEntriesScrollDisplayTl;
    long entryBundleId;
    long target_lang;
    String langCode;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView rvFormBundlesScrollDisplayTl;
    RecyclerViewAdapterFormBundlesScrollDisplayTl recyclerViewAdapterFormBundlesScrollDisplayTl;

    DatabaseEntriesVernacularAdapter databaseEntriesVernacularAdapter;
    List<GetEntriesVernacularTableValues> entriesVernacularList;



    final View.OnClickListener onClickListener = new MyOnClickListner();

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //TextView rowSenseBundleId;
        //TextView rowGlossId;
        //ImageView rowImage;
        TextView rowGloss;
        TextView rowClass;
        //TextView rowEntryRef;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //rowSenseBundleId = itemView.findViewById(R.id.sense_bundle_id);
            //rowGlossId = itemView.findViewById(R.id.gloss_id);
            rowGloss = itemView.findViewById(R.id.tvGlossScrollDisplayTl);
            rowClass = itemView.findViewById(R.id.tvClassScrollDisplayTl);
            //rowEntryRef = itemView.findViewById(R.id.tvEntryRefScrollDisplayTl);
            //rowImage = itemView.findViewById(R.id.item_image);
        }
    }

    public RecyclerViewAdapterEntriesScrollDisplayTl(Context context, List<GetEntriesGlossesTableValues> entriesGlossesList, RecyclerView rvEntriesScrollDisplayTl){
        this.context = context;
        this.entriesGlossesList = entriesGlossesList;
        this.rvEntriesScrollDisplayTl = rvEntriesScrollDisplayTl;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterEntriesScrollDisplayTl.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_entries_scroll_display_tl, viewGroup, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterEntriesScrollDisplayTl.ViewHolder viewHolder, int i) {

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


        GetEntriesGlossesTableValues gloss = entriesGlossesList.get(i);
        //viewHolder.rowSenseBundleId.setText(""+gloss.getSenseBundleId());
        //viewHolder.rowGlossId.setText(""+gloss.getGlossId());
        langCode = gloss.getLangCode();
        String langCodeSearch = context.getResources().getString(R.string.target_lang_code);
        target_lang = gloss.getTargetLang();
//        if (langCode == langCodeSearch) {
            viewHolder.rowGloss.setText("" + gloss.getGloss());
            viewHolder.rowClass.setText("(" + gloss.getClassName() + ")");
            //viewHolder.rowEntryRef.setText(""+gloss.getEntryRef());
            //String imageString = country.getImage();
            //int image = context.getResources().getIdentifier(imageString, "drawable", context.getPackageName());
            //viewHolder.rowImage.setImageResource(image);


            entryBundleId = gloss.getEntryBundleId();

            databaseEntriesVernacularAdapter = new DatabaseEntriesVernacularAdapter(context, entryBundleId, 0, 0);
            entriesVernacularList = databaseEntriesVernacularAdapter.getEntriesVernacular();
            rvFormBundlesScrollDisplayTl = viewHolder.itemView.findViewById(R.id.rvFormBundlesScrollDisplayTl);
            rvFormBundlesScrollDisplayTl.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(context);
            rvFormBundlesScrollDisplayTl.setLayoutManager(layoutManager);
            recyclerViewAdapterFormBundlesScrollDisplayTl = new RecyclerViewAdapterFormBundlesScrollDisplayTl(context, entriesVernacularList, rvFormBundlesScrollDisplayTl);
            rvFormBundlesScrollDisplayTl.setAdapter(recyclerViewAdapterFormBundlesScrollDisplayTl);

//        }






    }

    @Override
    public int getItemCount() {
        return entriesGlossesList.size();
    }

    private class MyOnClickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = rvEntriesScrollDisplayTl.getChildLayoutPosition(v);
            String gloss = entriesGlossesList.get(itemPosition).getGloss();
            //String entryRef = entriesGlossesList.get(itemPosition).getEntryRef();
            long entryBundleId = entriesGlossesList.get(itemPosition).getEntryBundleId();
            long senseBundleId = entriesGlossesList.get(itemPosition).getSenseBundleId();
            long glossId = entriesGlossesList.get(itemPosition).getGlossId();

            //Toast.makeText(context, gloss, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ViewAdapterEntryBundles.class);
            intent.putExtra("entry_bundle_id", entryBundleId);
            intent.putExtra("sense_bundle_id", senseBundleId);
            intent.putExtra("gloss_id", glossId);
            intent.putExtra("gloss", gloss);
            //intent.putExtra("entry_ref", entryRef);
            context.startActivity(intent);


        }
    }
}
