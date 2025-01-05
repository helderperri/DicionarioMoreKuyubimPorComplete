package japiim.dic.morekuyubim.por.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseTargetLettersAdapter;

import japiim.dic.morekuyubim.por.get_table_values.GetTargetLettersTableValues;
import japiim.dic.morekuyubim.por.R;

public class AlphabeticTargetFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout mTabLayout;


    DatabaseTargetLettersAdapter databaseTargetLettersAdapter;
    List<GetTargetLettersTableValues> targetLettersList = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.activity_dynamic, container, false);


        initViews(root);


        return root;
    }

    private void initViews(View v){
        databaseTargetLettersAdapter = new DatabaseTargetLettersAdapter(getContext(), 0);
        targetLettersList = databaseTargetLettersAdapter.getTargetLetters();

        viewPager = v.findViewById(R.id.viewpager);
        mTabLayout =  v.findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setDynamicFragmentToTabLayout();

    }

    private void setDynamicFragmentToTabLayout() {
        for (int i=0; i<targetLettersList.size(); i++)
        {
            GetTargetLettersTableValues getTargetLettersTableValues = targetLettersList.get(i);
            String glyph = getTargetLettersTableValues.getGlyph();

            mTabLayout.addTab(mTabLayout.newTab().setText(glyph));

        }
        AlphabeticTargetFragmentAdapter mDynamicFragmentAdapter = new AlphabeticTargetFragmentAdapter(requireActivity().getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mDynamicFragmentAdapter);
        viewPager.setCurrentItem(0);
    }


}