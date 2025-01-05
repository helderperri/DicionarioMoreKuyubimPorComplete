package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static android.os.Build.VERSION.SDK_INT;
import static japiim.dic.morekuyubim.por.MainActivity.nightMode;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseClassesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesDefsAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesVernacularAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseSenseBundlesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseSensesAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetClassesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesDefsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesVernacularTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetFormsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSenseBundlesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSensesTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.ViewAdapterEntryBundles;

public class RecyclerViewAdapterEntriesScrollDisplaySl extends RecyclerView.Adapter<RecyclerViewAdapterEntriesScrollDisplaySl.ViewHolder> {

    Context context;
    List<GetFormsTableValues> formsList;
    RecyclerView rvEntriesScrollDisplaySl;

    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterSenseBundlesScrollDisplaySl recyclerViewAdapterSenseBundlesScrollDisplaySl;
    DatabaseSenseBundlesAdapter databaseSenseBundlesAdapter;
    //RecyclerView rvGlosses;
    List<GetSenseBundlesTableValues> senseBundlesList = new ArrayList<>();
    long senseBundleId;
    long formBundleId;
    long entryBundleId;
    long entryId;
    RecyclerView rvSenseBundlesScrollDisplaySl;

    DatabaseEntriesVernacularAdapter databaseEntriesVernacularAdapter;
    List<GetEntriesVernacularTableValues> entriesVernacularList;


    RecyclerView rvFormBundlesScrollDisplayTl;


    String vernacular;
    String def;
    String partOfSpeech;


    DatabaseSensesAdapter databaseSensesAdapter;
    List<GetSensesTableValues> sensesList;


    DatabaseEntriesDefsAdapter databaseEntriesDefsAdapter;
    List<GetEntriesDefsTableValues> entriesDefsList;

    DatabaseClassesAdapter databaseClassesAdapter;
    List<GetClassesTableValues> classesList;

    final public View.OnClickListener onClickListener = new MyOnClickListner();

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //TextView rowSenseBundleId;
        //TextView rowFormId;
        //ImageView rowImage;

        TextView rowVernacular;
        TextView rowLangCode;
        //TextView rowClass;
        //TextView rowDef;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //rowSenseBundleId = itemView.findViewById(R.id.sense_bundle_id);
            //rowFormId = itemView.findViewById(R.id.gloss_id);
            rowVernacular = itemView.findViewById(R.id.tvVernacularScrollDisplaySl);
            rowLangCode = itemView.findViewById(R.id.tvLangCode);
            //rowImage = itemView.findViewById(R.id.item_image);
            //rowDef = itemView.findViewById(R.id.tvDefScrollDisplay);
            //rowClass = itemView.findViewById(R.id.tvClassScrollDisplay);

        }
    }

    public RecyclerViewAdapterEntriesScrollDisplaySl(Context context, List<GetEntriesVernacularTableValues> entriesVernacularList, RecyclerView rvEntriesScrollDisplaySl){
        this.context = context;
        this.entriesVernacularList = entriesVernacularList;
        this.rvEntriesScrollDisplaySl = rvEntriesScrollDisplaySl;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterEntriesScrollDisplaySl.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_entries_scroll_display_sl, viewGroup, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


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


        GetEntriesVernacularTableValues entryVernacular = entriesVernacularList.get(i);

        int numberOfSls = context.getResources().getInteger(R.integer.number_of_sls);

        viewHolder.rowVernacular.setText(""+entryVernacular.getVernacular());

        if(numberOfSls == 1){
            viewHolder.rowLangCode.setText("");

        }else if (numberOfSls >= 2){

            viewHolder.rowLangCode.setText(" ["+entryVernacular.getLangCode()+"]");

        }

        long source_lang = entryVernacular.getSourceLang();


        if(source_lang==1) {


            if(nightMode ==1){

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_01_dark));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_01_dark));

            }else{

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_01));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_01));

            }

        }else if(source_lang==2){

            if(nightMode ==1){

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_02_dark));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_02_dark));

            }else{

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_02));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_02));

            }

        }

        entryBundleId = entryVernacular.getEntryBundleId();

        databaseEntriesDefsAdapter = new DatabaseEntriesDefsAdapter(context, entryBundleId,0, 0);
        entriesDefsList = databaseEntriesDefsAdapter.getEntriesDefs();
        rvSenseBundlesScrollDisplaySl = viewHolder.itemView.findViewById(R.id.rvSenseBundlesScrollDisplaySl);
        rvSenseBundlesScrollDisplaySl.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvSenseBundlesScrollDisplaySl.setLayoutManager(layoutManager);
        recyclerViewAdapterSenseBundlesScrollDisplaySl = new RecyclerViewAdapterSenseBundlesScrollDisplaySl(context, entriesDefsList, rvSenseBundlesScrollDisplaySl);
        rvSenseBundlesScrollDisplaySl.setAdapter(recyclerViewAdapterSenseBundlesScrollDisplaySl);



    }

    @Override
    public int getItemCount() {
        return entriesVernacularList.size();
    }

    private class MyOnClickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = rvEntriesScrollDisplaySl.getChildLayoutPosition(v);
            String vernacular = entriesVernacularList.get(itemPosition).getVernacular();
            //String entryRef = formsList.get(itemPosition).getEntryRef();
            long formBundleId = entriesVernacularList.get(itemPosition).getFormBundleId();
            long entryBundleId = entriesVernacularList.get(itemPosition).getEntryBundleId();
            //long formId = formsList.get(itemPosition).getFormId();

            //Toast.makeText(context, vernacular, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ViewAdapterEntryBundles.class);
            intent.putExtra("form_bundle_id", formBundleId);
            intent.putExtra("entry_bundle_id", entryBundleId);
            //intent.putExtra("form_id", formId);
            //intent.putExtra("vernacular", vernacular);
            //intent.putExtra("entry_ref", entryRef);
            context.startActivity(intent);


        }
    }
}
