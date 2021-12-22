package com.mycompany.myapp.studiume;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.florent37.awesomebar.ActionItem;
import com.github.florent37.awesomebar.AwesomeBar;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.rom4ek.arcnavigationview.ArcNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Assessment_Adapter.OnGridClickedListener{


    @BindView(R.id.nav_view) ArcNavigationView navigationView;
    @BindView(R.id.toolbare) AwesomeBar toolbar;
    @BindView(R.id.toolbar) Toolbar toolbar1;
    public static final int OBJECTIVES = 0;
    public static final int THEORY = 1;
    public static final int  GERMAN = 2;
    Assessment_Adapter assessment_adapter;
    List<Assessment> assessmentList = new ArrayList<>();
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.assessment_recycler) RecyclerView recyclerView;
//    @BindView(R.id.fabs) FloatingActionButton fabs;
    @BindView(R.id.parentShimmerLayout) ShimmerFrameLayout parentShimmerLayout;
    @BindView(R.id.fabs) FloatingActionButton fabs;
    @BindView(R.id.fab_menu) com.github.clans.fab.FloatingActionMenu fam;
    @BindView(R.id.fab1)
    com.github.clans.fab.FloatingActionButton fab1;
    @BindView(R.id.fab2)
    com.github.clans.fab.FloatingActionButton fab2;
    @BindView(R.id.fab3)
    com.github.clans.fab.FloatingActionButton fab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
//         toolbar = (AwesomeBar) findViewById(R.id.toolbare);
//         Toolbar toolbar1 = (Toolbar)findViewById(R.id.toolbar);
         setSupportActionBar(toolbar1);

        toolbar.addAction(R.drawable.awsb_ic_edit_animated, "search");

        toolbar.setActionItemClickListener(new AwesomeBar.ActionItemClickListener() {
            @Override
            public void onActionItemClicked(int position, ActionItem actionItem) {
                Toast.makeText(getBaseContext(), actionItem.getText()+" clicked", Toast.LENGTH_LONG).show();
            }
        });
        toolbar.displayHomeAsUpEnabled(false);

//        recyclerView = (RecyclerView)findViewById(R.id.assessment_recycler);

//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar1, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setOnMenuClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.START);
            }
        });
//        toolbar.addOverflowItem("overflow 1");
//        toolbar.addOverflowItem("overflow 2");
        toolbar.setOverflowActionItemClickListener(new AwesomeBar.OverflowActionItemClickListener() {
            @Override
            public void onOverflowActionItemClicked(int position, String item) {

            }
        });
        parentShimmerLayout.startShimmerAnimation();
        //        navigationView = (ArcNavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
//        ImageView objicon = new ImageView(this);
//
//        objicon.setImageDrawable(getResources().getDrawable(R.drawable.add));
//        ImageView germanicon = new ImageView(this);
////        germanicon.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//        germanicon.setImageDrawable(getResources().getDrawable(R.drawable.add));
//        ImageView theoryicon = new ImageView(this);
////        theoryicon.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//        theoryicon.setImageDrawable(getResources().getDrawable(R.drawable.add));
//        SubActionButton obj = itemBuilder.setContentView(objicon)
//                .setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark)).build();
//        SubActionButton german = itemBuilder.setContentView(germanicon)
//                .setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark))
//                .build();
////        german.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//        SubActionButton theory = itemBuilder.setContentView(theoryicon)
//                .setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark))
//                .build();
////        theory.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
////        fabs = (FloatingActionButton)findViewById(R.id.fabs);
//        FloatingActionMenu floatingActionMenu = new FloatingActionMenu.Builder(this)
//                                                   .addSubActionView(obj)
//                                                   .addSubActionView(german)
//                                                   .addSubActionView(theory)
//                                                   .setStartAngle(180)
//                                                   .setEndAngle(270)
//                                                    .attachTo(fabs)
//                                                    .build();


        assessment_adapter = new Assessment_Adapter(this, this, assessmentList);
        assessment_adapter.setOnItemClickedListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(assessment_adapter);
        CountDownTimer countDownTimer = new CountDownTimer(3000,3000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                parentShimmerLayout.stopShimmerAnimation();
                parentShimmerLayout.setVisibility(View.GONE);

         setUpAssessments();
            }
        }.start();
        fabs.setOnClickListener(fabsListener);
   fab1.setOnClickListener(objListener);
   fab2.setOnClickListener(germanListener);
   fab3.setOnClickListener(theoryListener);
        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                    fam.setVisibility(View.GONE);
                    fabs.setVisibility(View.VISIBLE);
                }
            }
        });
        setUpAssessments();
    }
    public void setUpAssessments(){
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        LiveData<List<Info>> infos = mDataBase.infoDao().LoadAllInfo();
        viewModel.getAssessments().observe(this, new Observer<List<Assessment>>() {
                    @Override
                    public void onChanged(@Nullable List<Assessment> assessments) {
//                Processors.getInstance().setInfod(infos);
//                        List<Assessment> anexplore_Articles = new ArrayList<Assessment>();
//                        anexplore_Articles.clear();
//                        if (anexplore_Articles.size() > 0) {
//                            for (int i = 0; i < anexplore_Articles.size(); i++) {
////                            if (questions.get(i).getMclass().equals(name)) {
////                    if (questions.get(i).isAnswered()) {
////                                    questions.get(i).setRead(Spreferences.ReadState(mysharedpreference, questions.get(i).getQueid(), myeditor, questions.get(i).getNoanswer(), questions.get(i).getNocomment()));

                        if(assessments!=null) {
                            Collections.sort(assessments, new Comparator<Assessment>() {
                                @Override
                                public int compare(Assessment t0, Assessment t1) {
                                    return (int) (t1.getTimestamp() - t0.getTimestamp());
                                }
                            });

                        }
                            assessment_adapter.setAssessmentList(assessments);
                            assessment_adapter.notifyDataSetChanged();
//                        infoAdapter.setInfoLists(anquestions);

//                    Information.InfoListsand = Processors.getInstance().getInfod();
//                    Information.infoAdapterand.notifyDataSetChanged();
                        }

                    }
        );

                }

    View.OnClickListener objListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           processView(HomeActivity.OBJECTIVES);
           fam.close(true);
       }
   };

    View.OnClickListener germanListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            processView(HomeActivity.GERMAN);
            fam.close(true);

        }
    };

    View.OnClickListener theoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            processView(HomeActivity.THEORY);
            fam.close(true);

        }
    };

    View.OnClickListener fabsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fabs.setVisibility(View.GONE);
      fam.setVisibility(View.VISIBLE);
      fam.open(true);
        }
    };

    public void processView(int type){
        Intent intent = new Intent(HomeActivity.this,LoadingActiivity.class);
        intent.putExtra("question_type",type);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public void OnGridClicked(int position) {
Intent intent = new Intent(HomeActivity.this, InnerQuestion.class);
 Assessment assessment = assessment_adapter.getAssessmentList().get(position);
 String assessmentname = assessment.getAssessmetntName();
// displayo(assessmentname);
 intent.putExtra(InnerQuestion.ASSESSMENT_NAME,assessmentname);
 intent.putExtra(InnerQuestion.ASSESSMENT_TYPE,assessment.getType());
startActivity(intent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        }


        else if (id == R.id.nav_profile) {
Intent intent = new Intent(HomeActivity.this, profile_activity.class);
startActivity(intent);
        }

        else if (id == R.id.nav_voucher) {
Intent intent = new Intent(HomeActivity.this,Adminn.class);
startActivity(intent);
        }

        else if (id == R.id.nav_results) {
            Intent intent = new Intent(HomeActivity.this,Result_activity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_results) {

        }

        else if (id == R.id.nav_settings) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    protected void onStop() {
        super.onStop();
        parentShimmerLayout.stopShimmerAnimation();
    }


    public void displayo(String ji) {
        Context context = this;
        String msg = ji;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, msg, duration);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView myTextView = new TextView(context);
        ImageView cv = new ImageView(context);
        cv.setImageResource(R.drawable.ann);
        myTextView.setText(msg);
        Resources g = getResources();
        myTextView.setTextColor(Color.parseColor("#0a2149"));
        myTextView.setBackgroundDrawable(g.getDrawable(android.R.drawable.editbox_background));
        int lHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
        int lWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
        ll.addView(cv, new LinearLayout.LayoutParams(lHeight, lWidth));
        ll.addView(myTextView, new LinearLayout.LayoutParams(lHeight, lWidth));
        ll.setPadding(40, 50, 0, 50);
        toast.setView(ll);
        toast.show();
    }

}