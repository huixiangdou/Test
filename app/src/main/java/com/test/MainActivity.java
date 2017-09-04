package com.test;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.test.adapter.RecipeAdapter;
import com.test.basic.BasicActivity;
import com.test.moel.Recipe;

import java.util.ArrayList;

public class MainActivity extends BasicActivity {
    private int all = 12;
    private int mInt = 0;
    private GetDataRecipe getDataRecipe;
    private ProgressBar progressBar;
    private View view;
    private RecipeAdapter recipeAdapter;
    private RecyclerView recipeRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private Context context;
    private ArrayList<Recipe> recipeArrayList = new ArrayList<Recipe>();
    private LRecyclerView lRecyclerView = null;
    private LRecyclerViewAdapter lRecyclerViewAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_tool);
        setSupportActionBar(toolbar);


        getDataRecipe = new GetDataRecipe(this,this);
        mLayoutManager = new LinearLayoutManager(context);
        getData();
        lRecyclerView = (LRecyclerView) findViewById(R.id.recipe_recycler);
        recipeAdapter = new RecipeAdapter(recipeArrayList);
        lRecyclerView.setLayoutManager(mLayoutManager);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(recipeAdapter);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                recipeAdapter.clear();
                lRecyclerViewAdapter.notifyDataSetChanged();
                mInt = 0;
                getData();

            }
        });
        lRecyclerView.setLoadMoreEnabled(true);
        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getData();
            }
        });


//        lRecyclerView.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");
//        lRecyclerView.refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation,menu);

        MenuItem shareItem = menu.findItem(R.id.item_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Message");
        shareActionProvider.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);
    }

    public void button(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    public void getData(){
        if (mInt < all){
            getDataRecipe.getDatas("byclass","classid","2",mInt);
        }else {
            lRecyclerView.setNoMore(true);
        }
    }

    @Override
    public void onSucess(ArrayList arrayList) {
        mInt = mInt + arrayList.size();
        ArrayList<Recipe> recipes = arrayList;
        recipeArrayList.addAll(arrayList);
        lRecyclerView.refreshComplete(8);
        lRecyclerViewAdapter.notifyDataSetChanged();


//        recipeRecyclerView.setAdapter(recipeAdapter);
//        recipeAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, Object object) {
//                Recipe recipe = (Recipe) object;
//                Intent intent = new Intent(context,ContentActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("RECIPE",recipe);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.item_home:
//                Toast.makeText(this,"item_home",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.item_dashboard:
//                Toast.makeText(this,"item_bashboard",Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return true;
//    }
}
