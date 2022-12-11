package org.pytorch.demo.objectdetection;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;

import java.util.ArrayList;


public class fragment2 extends Fragment {

    private ArrayList<Favorite> favoritesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FavoriteAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment2, container, false);

        //recyclerview
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mAdapter = new FavoriteAdapter(favoritesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareData();
    }

    //데이터 준비(최종적으로는 동적으로 추가하거나 삭제할 수 있어야 한다. 이 데이터를 어디에 저장할지 정해야 한다.)
    private void prepareData() {
        favoritesList.add(new Favorite("서울시청",37.54892296550104,126.99089033876304));
        favoritesList.add(new Favorite("경복궁",37.54892296550104,126.99089033876304));
        favoritesList.add(new Favorite("서울역",37.54892296550104,126.99089033876304));
        favoritesList.add(new Favorite("남산",37.54892296550104,126.99089033876304));
        favoritesList.add(new Favorite("을지로입구역",37.54892296550104,126.99089033876304));
    }

}