package japiim.dic.morekuyubim.por.recycle_view_adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseExamplesTranslationAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseExamplesVernacularAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetExampleBundlesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetExamplesTranslationTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetExamplesVernacularTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterExampleBundles extends RecyclerView.Adapter<RecyclerViewAdapterExampleBundles.ViewHolder> {

    Context context;
    List<GetExampleBundlesTableValues> exampleBundlesList;
    RecyclerView rvExampleBundles;

    List<GetExamplesVernacularTableValues> examplesVernacularList = new ArrayList<>();
    DatabaseExamplesVernacularAdapter databaseExamplesVernacularAdapter;
    RecyclerView rvExamplesVernacular;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterExamplesVernacular recyclerViewAdapterExamplesVernacular;


    List<GetExamplesTranslationTableValues> examplesTranslationList = new ArrayList<>();
    DatabaseExamplesTranslationAdapter databaseExamplesTranslationAdapter;
    RecyclerView rvExamplesTranslation;
    RecyclerViewAdapterExamplesTranslation recyclerViewAdapterExamplesTranslation;

    long exampleBundleId;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowExampleBundleId;
        RecyclerView rowExampleVernacular;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //rowExampleBundleId = itemView.findViewById(R.id.tvExampleBundleId);
            rowExampleVernacular = itemView.findViewById(R.id.rvExamplesVernacular);
        }
    }

    public RecyclerViewAdapterExampleBundles(Context context, List<GetExampleBundlesTableValues> exampleBundlesList, RecyclerView rvExampleBundles){
        this.context = context;
        this.exampleBundlesList = exampleBundlesList;
        this.rvExampleBundles = rvExampleBundles;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterExampleBundles.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_example_bundle, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);



        return viewHolder;
    }

 public void onBindViewHolder (@NonNull RecyclerViewAdapterExampleBundles.ViewHolder viewHolder, int i) {
        GetExampleBundlesTableValues getExampleBundlesTableValues = exampleBundlesList.get(i);
        //viewHolder.rowSenseBundleId.setText(""+gloss.getSenseBundleId());
        //viewHolder.rowExampleBundleId.setText("example bundle id: "+getExampleBundlesTableValues.getExampleBundleId());

        exampleBundleId = getExampleBundlesTableValues.getExampleBundleId();


        databaseExamplesVernacularAdapter = new DatabaseExamplesVernacularAdapter(context, exampleBundleId, 0);
        examplesVernacularList = databaseExamplesVernacularAdapter.getExamplesVernacular();
        rvExamplesVernacular = viewHolder.itemView.findViewById(R.id.rvExamplesVernacular);
        rvExamplesVernacular.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvExamplesVernacular.setLayoutManager(layoutManager);
        recyclerViewAdapterExamplesVernacular = new RecyclerViewAdapterExamplesVernacular(context, examplesVernacularList, rvExamplesVernacular);
        rvExamplesVernacular.setAdapter(recyclerViewAdapterExamplesVernacular);



         databaseExamplesTranslationAdapter = new DatabaseExamplesTranslationAdapter(context, exampleBundleId, 0);
         examplesTranslationList = databaseExamplesTranslationAdapter.getExamplesTranslation();
         rvExamplesTranslation = viewHolder.itemView.findViewById(R.id.rvExamplesTranslation);
         rvExamplesTranslation.setHasFixedSize(true);
         layoutManager = new LinearLayoutManager(context);
         rvExamplesTranslation.setLayoutManager(layoutManager);
         recyclerViewAdapterExamplesTranslation = new RecyclerViewAdapterExamplesTranslation(context, examplesTranslationList, rvExamplesTranslation);
         rvExamplesTranslation.setAdapter(recyclerViewAdapterExamplesTranslation);


 }
    public void onDestroy() {
        // TODO Auto-generated method stub

        recyclerViewAdapterExamplesVernacular.onDestroy();
        Log.d("Main", " GNM-JPP ON DESTROY EXAMPLE BUNDLES ADAPTER");
    }

    @Override
    public int getItemCount() {
        return exampleBundlesList.size();
    }


}
