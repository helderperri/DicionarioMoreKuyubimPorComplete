package japiim.dic.morekuyubim.por.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesSdsAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesSdsTableValues;

public class SemanticSourceFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout mTabLayout;

    DatabaseEntriesSdsAdapter databaseEntriesSdsAdapter;

    List<GetEntriesSdsTableValues> entriesSdsList = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

        View view;
        view = inflater.inflate(R.layout.activity_dynamic, container, false);


        Log.d("GNM-JPP", "onCreateView SOURCE SEMANTIC");


        initViews(view);
        return view;
    }


    private void initViews(View v){


        databaseEntriesSdsAdapter = new DatabaseEntriesSdsAdapter(getContext(), 0, "");
        entriesSdsList = databaseEntriesSdsAdapter.getSdsToSearchDisplay();



        mTabLayout =  v.findViewById(R.id.tabs);
        viewPager = v.findViewById(R.id.viewpager);

        // Por que setOffscreenPageLimit é 5 (valor original). Agora setado para 0 para debug?;
        viewPager.setOffscreenPageLimit(0);
        //a linha abaixo guarda a posição na semantic source tab
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
                Log.d("GNM-JPP", "onTabSelected SOURCE SEMANTIC tba-position: ");

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                String numberOfSls_s = "1";
//                numberOfSls_s = valueOf(numberOfSls).toString();

                Log.d("GNM-JPP", "onTabUnselected SOURCE SEMANTIC"+numberOfSls_s);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                String numberOfSls_s = "1";
//                numberOfSls_s = valueOf(numberOfSls).toString();
                Log.d("GNM-JPP", "onTabReelected SOURCE SEMANTIC"+numberOfSls_s);

            }
        });

        setDynamicFragmentToTabLayout();

    }

    private void setDynamicFragmentToTabLayout() {
        for (int i=0; i<entriesSdsList.size(); i++)
        {
            GetEntriesSdsTableValues getEntriesSdsTableValues = entriesSdsList.get(i);


            String sdName = getEntriesSdsTableValues.getSdName();

            mTabLayout.addTab(mTabLayout.newTab().setText(sdName));
        }
        SemanticSourceFragmentAdapter mDynamicFragmentAdapter = new SemanticSourceFragmentAdapter(requireActivity().getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mDynamicFragmentAdapter);
        viewPager.setCurrentItem(0);
    }


}