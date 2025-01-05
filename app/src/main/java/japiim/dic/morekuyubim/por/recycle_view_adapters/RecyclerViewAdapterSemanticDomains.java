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

import japiim.dic.morekuyubim.por.database_adapters.DatabaseExamplesVernacularAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseSemanticDomainNamesAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetExamplesVernacularTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSemanticDomainNamesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSemanticDomainsTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterSemanticDomains extends RecyclerView.Adapter<RecyclerViewAdapterSemanticDomains.ViewHolder> {

    Context context;
    List<GetSemanticDomainsTableValues> semanticDomainsList;
    RecyclerView rvSemanticDomains;

    List<GetExamplesVernacularTableValues> examplesVernacularList = new ArrayList<>();
    DatabaseExamplesVernacularAdapter databaseExamplesVernacularAdapter;
    RecyclerView rvExamplesVernacular;
    RecyclerView.LayoutManager layoutManager;

  long semanticDomainId;
    String semanticDomainName;

    DatabaseSemanticDomainNamesAdapter databaseSemanticDomainNamesAdapter;
    List<GetSemanticDomainNamesTableValues> semanticDomainNamesList = new ArrayList<>();


    public static class ViewHolder extends RecyclerView.ViewHolder{
      //  TextView rowSemanticDomainId;
        TextView rowSemanticDomainName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
    //        rowSemanticDomainId = itemView.findViewById(R.id.tvSemanticDomainId);
            rowSemanticDomainName = itemView.findViewById(R.id.tvSemanticDomain);
        }
    }

    public RecyclerViewAdapterSemanticDomains(Context context, List<GetSemanticDomainsTableValues> semanticDomainsList, RecyclerView rvSemanticDomains){
        this.context = context;
        this.semanticDomainsList = semanticDomainsList;
        this.rvSemanticDomains = rvSemanticDomains;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterSemanticDomains.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_semantic_domain, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);



        return viewHolder;
    }

 public void onBindViewHolder (@NonNull RecyclerViewAdapterSemanticDomains.ViewHolder viewHolder, int i) {
        GetSemanticDomainsTableValues getSemanticDomainsTableValues = semanticDomainsList.get(i);
        //
     semanticDomainId = getSemanticDomainsTableValues.getSemanticDomainId();
  //       viewHolder.rowSemanticDomainId.setText("sd id: "+semanticDomainId);

     semanticDomainName = getSemanticDomainsTableValues.getSemanticDomainName();

     //databaseSemanticDomainNamesAdapter = new DatabaseSemanticDomainNamesAdapter(context, semanticDomainId, 0);
     //databaseSemanticDomainNamesAdapter = new DatabaseSemanticDomainNamesAdapter(context, semanticDomainId, 0);
     //semanticDomainName = getSemanticDomainsTableValues.getSemanticDomainName();
    viewHolder.rowSemanticDomainName.setText(""+semanticDomainName);
     //viewHolder.rowSemanticDomainName.setText("sdsdsd"+semanticDomainName);





 }

    @Override
    public int getItemCount() {
        return semanticDomainsList.size();
    }


}
