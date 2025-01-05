package japiim.dic.morekuyubim.por.recycle_view_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseClassesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesDefsAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseGlossesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseSensesAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetClassesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesDefsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetGlossesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSensesTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterSenseBundlesScrollDisplaySl extends RecyclerView.Adapter<RecyclerViewAdapterSenseBundlesScrollDisplaySl.ViewHolder> {

    Context context;
    DatabaseEntriesDefsAdapter databaseEntriesDefsAdapter;
    List<GetEntriesDefsTableValues> entriesDefsList;
    RecyclerView rvSenseBundles;
    RecyclerView rvGlossesScrollDisplaySl;
    RecyclerView.LayoutManager layoutManager;
    DatabaseGlossesAdapter databaseGlossesAdapter;
    RecyclerViewAdapterGlossesScrollDisplaySl recyclerViewAdapterGlossesScrollDisplaySl;
    List<GetGlossesTableValues> glossesList = new ArrayList<>();
    long senseBundleId;
    long entryBundleId;
    String def;
    String partOfSpeech;

    DatabaseSensesAdapter databaseSensesAdapter;
    List<GetSensesTableValues> sensesList;

    DatabaseClassesAdapter databaseClassesAdapter;
    List<GetClassesTableValues> classesList;

RecyclerViewAdapterEntriesScrollDisplaySl recyclerViewAdapterEntriesScrollDisplaySl;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        //TextView rowSenseBundleId;
        //TextView rowFormId;
        //ImageView rowImage;
        //TextView rowSenseBundleId;
        TextView rowClass;
        TextView rowDef;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //rowSenseBundleId = itemView.findViewById(R.id.sense_bundle_id);
            //rowFormId = itemView.findViewById(R.id.senseBundle_id);
            //rowSenseBundleId = itemView.findViewById(R.id.tvSenseBundleIdScrollDisplaySl);
            rowDef = itemView.findViewById(R.id.tvDefScrollDisplay);
            rowClass = itemView.findViewById(R.id.tvClassScrollDisplay);
            //rowImage = itemView.findViewById(R.id.item_image);
        }
    }

    public RecyclerViewAdapterSenseBundlesScrollDisplaySl(Context context, List<GetEntriesDefsTableValues> entriesDefsList, RecyclerView rvSenseBundles){
        this.context = context;
        this.entriesDefsList = entriesDefsList;
        this.rvSenseBundles = rvSenseBundles;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterSenseBundlesScrollDisplaySl.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_sense_bundles_scroll_display_sl, viewGroup, false);
        //view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterSenseBundlesScrollDisplaySl.ViewHolder viewHolder, int i) {
        GetEntriesDefsTableValues entryDef = entriesDefsList.get(i);
        //viewHolder.rowSenseBundleId.setText(""+senseBundle.getSenseBundleId());
        //viewHolder.rowFormId.setText(""+senseBundle.getFormId());
        //viewHolder.rowSenseBundleId.setText(""+senseBundle.getSenseBundleId());
        //viewHolder.rowEntryRef.setText(""+senseBundle.getEntryRef());
        //String imageString = country.getImage();
        //int image = context.getResources().getIdentifier(imageString, "drawable", context.getPackageName());
        //viewHolder.rowImage.setImageResource(image);


        def = entryDef.getDef();
        viewHolder.rowDef.setText(""+ def);
        partOfSpeech = entryDef.getClassName();
        viewHolder.rowClass.setText("("+ partOfSpeech+")");



        /*
        databaseGlossesAdapter = new DatabaseGlossesAdapter(context, senseBundleId, 0);
        glossesList = databaseGlossesAdapter.getGlosses();
        rvGlossesScrollDisplaySl = viewHolder.itemView.findViewById(R.id.rvGlossesScrollDisplaySl);
        rvGlossesScrollDisplaySl.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvGlossesScrollDisplaySl.setLayoutManager(layoutManager);
        recyclerViewAdapterGlossesScrollDisplaySl = new RecyclerViewAdapterGlossesScrollDisplaySl(context, glossesList, rvGlossesScrollDisplaySl);
        rvGlossesScrollDisplaySl.setAdapter(recyclerViewAdapterGlossesScrollDisplaySl);

         */




    }


    @Override
    public int getItemCount() {
        return entriesDefsList.size();
    }

/*

    private class MyOnClickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = rvSenseBundles.getChildLayoutPosition(v);
            //String vernacular = entriesDefsList.get(itemPosition).getVernacular();
            //String entryRef = formsList.get(itemPosition).getEntryRef();
            //long formBundleId = entriesDefsList.get(itemPosition).getFormBundleId();
            long entryBundleId = entriesDefsList.get(itemPosition).getEntryBundleId();
            //long formId = formsList.get(itemPosition).getFormId();

            //Toast.makeText(context, ""+entryBundleId, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ViewAdapterEntryBundles.class);
            //intent.putExtra("form_bundle_id", formBundleId);
            intent.putExtra("entry_bundle_id", entryBundleId);
            //intent.putExtra("form_id", formId);
            //intent.putExtra("vernacular", vernacular);
            //intent.putExtra("entry_ref", entryRef);
            context.startActivity(intent);


        }
    }


 */
}
