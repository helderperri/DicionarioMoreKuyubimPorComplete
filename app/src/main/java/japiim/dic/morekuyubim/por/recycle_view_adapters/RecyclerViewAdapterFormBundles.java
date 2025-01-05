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

import japiim.dic.morekuyubim.por.database_adapters.DatabaseFormsAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetFormBundlesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetFormsTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterFormBundles extends RecyclerView.Adapter<RecyclerViewAdapterFormBundles.ViewHolder> {

    Context context;
    List<GetFormBundlesTableValues> formBundlesList;
    RecyclerView rvFormBundles;

    RecyclerView.LayoutManager layoutManager;

    DatabaseFormsAdapter databaseFormsAdapter;
    RecyclerView rvVernaculars;
    RecyclerViewAdapterVernaculars recyclerViewAdapterVernaculars;
    List<GetFormsTableValues> formsList = new ArrayList<>();
    long formBundleId;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        //TextView rowFormBundleId;
        //TextView rowGlossId;
        //ImageView rowImage;
        TextView rowFormBundleId;
        //TextView rowEntryId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //rowFormBundleId = itemView.findViewById(R.id.form_bundle_id);
            //rowGlossId = itemView.findViewById(R.id.gloss_id);
            //rowFormBundleId = itemView.findViewById(R.id.tvFormBundleId);
            //rowEntryId = itemView.findViewById(R.id.tvEntryId);
            //rowImage = itemView.findViewById(R.id.item_image);
        }
    }

    public RecyclerViewAdapterFormBundles(Context context, List<GetFormBundlesTableValues> formBundlesList, RecyclerView rvFormBundles){
        this.context = context;
        this.formBundlesList = formBundlesList;
        this.rvFormBundles = rvFormBundles;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterFormBundles.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_form_bundle, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterFormBundles.ViewHolder viewHolder, int i) {
        GetFormBundlesTableValues getFormBundlesTableValues = formBundlesList.get(i);
        //viewHolder.rowFormBundleId.setText("form bundle id: "+getFormBundlesTableValues.getFormBundleId());
        formBundleId = getFormBundlesTableValues.getFormBundleId();



        databaseFormsAdapter = new DatabaseFormsAdapter(context, formBundleId, 0);
        formsList = databaseFormsAdapter.getForms();
        rvVernaculars = viewHolder.itemView.findViewById(R.id.rvVernaculars);
        rvVernaculars.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvVernaculars.setLayoutManager(layoutManager);
        recyclerViewAdapterVernaculars = new RecyclerViewAdapterVernaculars(context, formsList, rvVernaculars);
        rvVernaculars.setAdapter(recyclerViewAdapterVernaculars);


    }
    public void onDestroy() {
        // TODO Auto-generated method stub

        recyclerViewAdapterVernaculars.onDestroy();
        Log.d("Main", " GNM-JPP ON DESTROY FORM BUNDLES ADAPTER");
    }

    @Override
    public int getItemCount() {
        return formBundlesList.size();
    }

}
