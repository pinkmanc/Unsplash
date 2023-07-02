package com.example.usplash.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.upsplash.R;
import com.example.usplash.data.dto.UnsplashPhoto;
import com.example.usplash.net.APIService;
import com.example.usplash.ui.adapter.UnsplashPTAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class UnsplashPTFragment extends Fragment {
    private SwipeRefreshLayout srl_refresh;
    private RecyclerView rec_pt;
    private CompositeDisposable mSubscriptions;
    private UnsplashPTAdapter mAdapter;
    private FloatingActionButton fab_top;
    private int mCurPage = 1;
    private ArrayList<UnsplashPhoto> mData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pt_content,container,false);
        srl_refresh=view.findViewById(R.id.srl_refresh);
        rec_pt=view.findViewById(R.id.rec_pt);
        fab_top=view.findViewById(R.id.fab_top);
        srl_refresh.setOnRefreshListener(()->{
            mCurPage=1;
            fetchUnsplash(true);
        });
        final GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        rec_pt.setLayoutManager(layoutManager);
        rec_pt.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager=(LinearLayoutManager) recyclerView.getLayoutManager();
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    if(layoutManager.getItemCount()-recyclerView.getChildCount()<=layoutManager.findFirstCompletelyVisibleItemPosition()){
                        ++mCurPage;
                        fetchUnsplash(false);
                    }
                }
                if(layoutManager.findFirstCompletelyVisibleItemPosition()!=0){
                    fabInAnim();
                }else {
                    fetchOutAnim();
                }
            }
        });

        fab_top.setOnClickListener(v->{
            LinearLayoutManager manager=(LinearLayoutManager) rec_pt.getLayoutManager();
            if(manager.findFirstCompletelyVisibleItemPosition()<50){
                rec_pt.smoothScrollToPosition(0);
            }else {
                rec_pt.scrollToPosition(0);
                fabOutAnim();
            }
        });
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSubscriptions=new CompositeDisposable();
        mData=new ArrayList<>();
        mA
    }

    private void fetchUnsplash(boolean isRefresh) {
        Disposable subscribe = APIService.getInstance().apis.fetchUnsplashPT(20,mCurPage)
    }
}
