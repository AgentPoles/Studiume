package com.mycompany.myapp.studiume;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;

public class Assessment_Adapter extends RecyclerView.Adapter<Assessment_Adapter.MyViewHolder> {

    private Context mContext;
    private List<Assessment> assessmentList;
    Assessment_Adapter.OnGridClickedListener Listener;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView assement_name, assessment_type, date, sample_question, first_letter;
        public ImageView completed, uploaded;
        public CardView cardView;
//        ImageView explore_image;
        public ConstraintLayout constraintLayout;

        public MyViewHolder(View view) {
            super(view);
            assement_name = (TextView) view.findViewById(R.id.assessment_name);
            assessment_type  = (TextView) view.findViewById(R.id.assessment_type);
            sample_question = (TextView) view.findViewById(R.id.sample_question);
            date = (TextView)view.findViewById(R.id.date_text);
            completed = (ImageView)view.findViewById(R.id.completed);
            first_letter = (TextView)view.findViewById(R.id.firstletter);
            uploaded = (ImageView)view.findViewById(R.id.uploaded);
//                otherThings = (TextView) view.findViewById(R.id.count);
//            thumbnail = (ImageView) view.findViewById(R.id.innerholderimage);
//            relinnerCalculator = (RelativeLayout)view.findViewById(R.id.relinnnercalculator);
//                overflow = (ImageView) view.findViewById(R.id.overflow);
            cardView = (CardView)view.findViewById(R.id.assessment_card);
        }
    }


    public Assessment_Adapter(Activity activity, Context mContext, List<Assessment> assessmentList) {
        this.mContext = mContext;
        this.assessmentList = assessmentList;
    }

    @Override
    public Assessment_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assessment_element_holder, parent, false);

        return new Assessment_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Assessment_Adapter.MyViewHolder holder, final int position) {

        holder.setIsRecyclable(false);
        final  Assessment assessment = assessmentList.get(position);
        holder.assement_name.setText(assessment.getAssessmetntName());
        holder.first_letter.setText(String.valueOf(assessment.getAssessmetntName().charAt(0)));
        if(position%3==0){
            holder.first_letter.setBackgroundResource(R.drawable.back_);
        }
        else {
            if(position%2==0){
                holder.first_letter.setBackgroundResource(R.drawable.back_g);
            }
            else {
                holder.first_letter.setBackgroundResource(R.drawable.back_b);
            }
        }
        switch (assessment.getType()) {
            case HomeActivity.OBJECTIVES: {holder.assessment_type.setText("type objectives");
                                            break;}
            case HomeActivity.THEORY: {holder.assessment_type.setText("type theory");
                break;}
            case HomeActivity.GERMAN: {holder.assessment_type.setText("type german");
                break;}
                default:break;

        }
        holder.sample_question.setText(assessment.getSample_question());
        holder.date.setText(assessment.getDate() + assessment.getTime());
        if(assessment.isIscomplete()) {
        holder.completed.setImageResource(R.drawable.fullstripblue);
        }
        if(assessment.isHas_been_asked()) {
            holder.uploaded.setImageResource(R.drawable.checkedgreen);
        }
//            holder.otherThings.setText(album.getOtherThing() );

        // loading album cover using Glide library
//            Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listener.OnGridClicked(position);
            }
        });
    }

/**
 * Showing popup menu when tapping on 3 dots
 //         */
//        private void showPopupMenu(View view) {
//            // inflate menu
//            PopupMenu popup = new PopupMenu(mContext, view);
//            MenuInflater inflater = popup.getMenuInflater();
//            inflater.inflate(R.menu.menu_calculator, popup.getMenu());
//            popup.setOnMenuItemClickListener(new com.mycompany.myapp.xline.CalculatorAdapter.MyMenuItemClickListener());
//            popup.show();
//        }

    /**
     * Click listener for popup menu items
     */
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//        public MyMenuItemClickListener() {
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.action_add_favourite:
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.action_play_next:
//                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                    return true;
//                default:
//            }
//            return false;
//        }
//    }

    @Override
    public int getItemCount() {
        return assessmentList.size();
    }

    public List<Assessment> getAssessmentList() {
        return assessmentList;
    }

    public interface OnGridClickedListener{
        public void OnGridClicked(int position );
    }
    public void setOnItemClickedListener (Assessment_Adapter.OnGridClickedListener listener){
        this.Listener = listener;
    }

    public void setArticlesList(List<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
    }

    public void setAssessmentList(List<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
    }


    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        activity.startPostponedEnterTransition();
                        return true;
                    }
                });
    }

}




