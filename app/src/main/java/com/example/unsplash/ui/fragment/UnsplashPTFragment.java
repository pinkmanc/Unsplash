package com.example.unsplash.ui.fragment;

import android.os.Bundle;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.upsplash.R;
import com.example.unsplash.data.dto.UnsplashPhoto;
import com.example.unsplash.net.APIService;
import com.example.unsplash.ui.adapter.UnsplashPTAdapter;
import com.example.unsplash.utils.ResUtils;
import com.example.unsplash.utils.RxSchedulers;
import com.example.unsplash.utils.ToastUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UnsplashPTFragment extends Fragment {
    private static final String API_KEY = "Client-ID 6I_Cls2BK4WsdccSABayphtKdAeDt-Tr_dXutq_eKDc";
    private SwipeRefreshLayout srl_refresh;
    private RecyclerView rec_pt;
    private CompositeDisposable mSubscriptions;
    private UnsplashPTAdapter mAdapter;
    private FloatingActionButton fab_top;
    private int mCurPage = 1;
    private ArrayList<UnsplashPhoto> mData;
    private final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    public static UnsplashPTFragment newInstance() {
        return new UnsplashPTFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pt_content,container,false);
        srl_refresh=view.findViewById(R.id.srl_refresh);
        rec_pt=view.findViewById(R.id.rec_pt);
        fab_top=view.findViewById(R.id.fab_top);
        srl_refresh.setOnRefreshListener(()->{
            mCurPage=1;
            fetchUnsplashPT(true);
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
                        fetchUnsplashPT(false);
                    }
                }
                if(layoutManager.findFirstCompletelyVisibleItemPosition()!=0){
                    fabInAnim();
                }else {
                    fabOutAnim();
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
        mAdapter=new UnsplashPTAdapter(getActivity(), mData);
        rec_pt.setAdapter(mAdapter);
        srl_refresh.setRefreshing(true);
        fetchUnsplashPT(true);
    }
    private void fetchUnsplashPT(boolean isRefresh) {
        Disposable subscribe = APIService.getInstance().apis.fetchUnsplashPT(API_KEY,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscription -> srl_refresh.setRefreshing(true))
                .doFinally(() -> srl_refresh.setRefreshing(false))
                .subscribe(data -> {
                    System.out.println(data);
                    if(data != null && data.getResults() != null && data.getResults().size() > 0) {
                        ArrayList<UnsplashPhoto> results = data.getResults();
                        if (isRefresh) {
                            mAdapter.addAll(results);
                            ToastUtils.shortToast(ResUtils.getString(R.string.refresh_success));
                        } else {
                            mAdapter.loadMore(results);
                            String msg = String.format(ResUtils.getString(R.string.load_more_num),results.size(),"好看的图片");
                            ToastUtils.shortToast(msg);
                        }
                    }
                }, RxSchedulers::processRequestException);

        mSubscriptions.add(subscribe);
    }
    /* 悬浮按钮显示动画 */
    private void fabInAnim() {
        if (fab_top.getVisibility() == View.GONE) {
            fab_top.setVisibility(View.VISIBLE);
            ViewCompat.animate(fab_top).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null).start();
        }
    }

    /* 悬浮图标隐藏动画 */
    private void fabOutAnim() {
        if (fab_top.getVisibility() == View.VISIBLE) {
            ViewCompat.animate(fab_top).scaleX(0.0F).scaleY(0.0F).alpha(0.0F)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {

                        }

                        @Override
                        public void onAnimationEnd(View view) {
                            fab_top.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(View view) {

                        }
                    }).start();
        }
    }

    private void fetchUnsplash(boolean isRefresh) {
        Disposable subscribe = APIService.getInstance().apis.fetchUnsplashPT(API_KEY,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscription -> srl_refresh.setRefreshing(true))
                .doFinally(() -> srl_refresh.setRefreshing(false))
                .subscribe(data -> {
                    if(data != null && data.getResults() != null && data.getResults().size() > 0) {
                        ArrayList<UnsplashPhoto> results = data.getResults();
                        if (isRefresh) {
                            mAdapter.addAll(results);
                            ToastUtils.shortToast(ResUtils.getString(R.string.refresh_success));
                        } else {
                            mAdapter.loadMore(results);
                            String msg = String.format(ResUtils.getString(R.string.load_more_num),results.size(),"妹子");
                            ToastUtils.shortToast(msg);
                        }
                    }
                }, RxSchedulers::processRequestException);
        mSubscriptions.add(subscribe);
    }
}
