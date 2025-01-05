
package japiim.dic.morekuyubim.por.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesGlossesSdsAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesSdsAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesGlossesSdsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesSdsTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntriesSdsScrollDisplayTl;


public class SemanticTargetFragmentNewInstance extends Fragment {
//    private RecyclerView rv;
    RecyclerViewAdapterEntriesSdsScrollDisplayTl recyclerViewAdapterEntriesSdsScrollDisplayTl;
    DatabaseEntriesSdsAdapter databaseEntriesSdsAdapter;

    RecyclerView.LayoutManager layoutManager;

    List<GetEntriesSdsTableValues> entriesSdsList = new ArrayList<>();


    DatabaseEntriesGlossesSdsAdapter databaseEntriesGlossesSdsAdapter;

    List<GetEntriesGlossesSdsTableValues> entriesGlossesList = new ArrayList<>();

    public static SemanticTargetFragmentNewInstance newInstance() {
        return new SemanticTargetFragmentNewInstance();
//        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.dynamic_fragment_layout, container, false);


        initViews(view);
        return view;
    }

    private void initViews(View view) {

            // Load tab items
            databaseEntriesSdsAdapter = new DatabaseEntriesSdsAdapter(getContext(), 0, "");
            entriesSdsList = databaseEntriesSdsAdapter.getSdsToSearchDisplay();


            // Load items' content
            int position = getArguments().getInt("position");
            //Log.e("semantic_target", entriesSdsList.size()+" "+posi+" w "+entriesSdsList.get(posi).getSdId());
            GetEntriesSdsTableValues getEntriesSdsTableValues = entriesSdsList.get(position);
            long sdId = getEntriesSdsTableValues.getSdId();
            databaseEntriesGlossesSdsAdapter = new DatabaseEntriesGlossesSdsAdapter(getContext(), 0, 0, sdId);
            entriesGlossesList = databaseEntriesGlossesSdsAdapter.getGlossesToSearchDisplayBySdId(sdId);


            RecyclerView rvEntries = view.findViewById(R.id.rvEntries);
            recyclerViewAdapterEntriesSdsScrollDisplayTl = new RecyclerViewAdapterEntriesSdsScrollDisplayTl(getActivity(), entriesGlossesList, rvEntries);

            rvEntries.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());

            rvEntries.setLayoutManager(layoutManager);

            rvEntries.setAdapter(recyclerViewAdapterEntriesSdsScrollDisplayTl);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("GNM-JPP", "onActivityCreated: FRAGMENT SEMANTIC TARGET");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("GNM-JPP", "onAttach: FRAGMENT SEMANTIC TARGET");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("GNM-JPP", "onPause: FRAGMENT SEMANTIC TARGET");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("GNM-JPP", "onResume: FRAGMENT SEMANTIC TARGET");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("GNM-JPP", "onStop: FRAGMENT SEMANTIC TARGET");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("GNM-JPP", "onDestroyView: FRAGMENT SEMANTIC TARGET");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("GNM-JPP", "onDestroy: FRAGMENT SEMANTIC TARGET");

    }
}
