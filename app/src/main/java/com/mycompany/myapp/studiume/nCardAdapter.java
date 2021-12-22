package com.mycompany.myapp.studiume;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

public class nCardAdapter extends RecyclerView.Adapter<nCardAdapter.MyViewHolder> {

    private Context mContext;
    private List<QuestionHolder> questionHolderList;
    nCardAdapter.OnGridClickedListener Listener;
    Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView question_image, answer_image,edit_single_question;
        private TextView question_body,options_a, options_b, options_c, options_d, options_e, question_number;
        private TextView answer_body, say_option, current_question_number, firat_letter;
        LinearLayout lin_options;
        //        ImageView explore_image;
        public ConstraintLayout constraintLayout;


        public MyViewHolder(View view) {
            super(view);
            question_body = (TextView)view.findViewById(R.id.questionet);
            answer_body = (TextView)view.findViewById(R.id.answer);
            options_a = (TextView)view.findViewById(R.id.options_a);
            options_b = (TextView)view.findViewById(R.id.options_b);
            options_c = (TextView)view.findViewById(R.id.options_c);
            options_d = (TextView)view.findViewById(R.id.options_d);
            options_e = (TextView)view.findViewById(R.id.options_e);
            say_option = (TextView)view.findViewById(R.id.say_option);

            lin_options = (LinearLayout)view.findViewById(R.id.lin_options);
            current_question_number = (TextView)view .findViewById(R.id.current_question_number);
            question_image = (ImageView)view.findViewById(R.id.question_image);
            answer_image = (ImageView)view.findViewById(R.id.answer_image);
            question_number = (TextView)view.findViewById(R.id.question_numher);
            edit_single_question = (ImageView)view.findViewById(R.id.edit_single_question);
//                otherThings = (TextView) view.findViewById(R.id.count);
//            thumbnail = (ImageView) view.findViewById(R.id.innerholderimage);
//            relinnerCalculator = (RelativeLayout)view.findViewById(R.id.relinnnercalculator);
//                overflow = (ImageView) view.findViewById(R.id.overflow);
//            cardView = (CardView)view.findViewById(R.id.assessment_card);
        }
    }


    public nCardAdapter(Activity activity, Context mContext, List<QuestionHolder> questionHolderList) {
        this.mContext = mContext;
        this.questionHolderList = questionHolderList;
    }

    @Override
    public nCardAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_question_view, parent, false);

        return new nCardAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final nCardAdapter.MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        final  QuestionHolder questionHolder = questionHolderList.get(position);
        //setting data to views
        holder.question_body.setText(questionHolder.getQuestion_body());
        holder.answer_body.setText(questionHolder.getAnswer_body());
        holder.current_question_number.setText(String.valueOf(position +1));
        if(questionHolder.getType()==HomeActivity.OBJECTIVES) {
            holder.lin_options.setVisibility(View.VISIBLE);
            holder.say_option.setVisibility(View.VISIBLE);
            holder.options_a.setText(String.valueOf(questionHolder.getOptions_a()));
            holder.options_b.setText(String.valueOf(questionHolder.getOptions_b()));
            holder.options_c.setText(String.valueOf(questionHolder.getOptions_c()));
            holder.options_d.setText(String.valueOf(questionHolder.getOptions_d()));
            holder.options_e.setText(String.valueOf(questionHolder.getOptions_e()));
        }
        if(questionHolder.isHas_answer_image()) {
            holder.answer_image.setVisibility(View.VISIBLE);
            holder.answer_image.setImageResource(R.drawable.ann);
        }
        if(questionHolder.getImage_id()!=null){
            holder.question_image.setVisibility(View.VISIBLE);
            newshowite(activity,Uri.parse(questionHolder.getImage_id().toString()),holder.question_image);
        }
        if((questionHolder.getAnswer_image_id()!=null)&&!questionHolder.getAnswer_image_id().equals("")){
            holder.answer_image.setVisibility(View.VISIBLE);
            newshowite(activity,Uri.parse(questionHolder.getAnswer_image_id()),holder.answer_image);
        }
        if(questionHolder.isHas_question_image()) {
            holder.question_image.setVisibility(View.VISIBLE);
            holder.question_image.setImageResource(R.drawable.checkedgreen);
        }
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Listener.OnGridClicked(position);
//            }
//        });
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

    @Override
    public int getItemCount() {
        return questionHolderList.size();
    }

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



    public List<QuestionHolder> getQuestionHolderList() {
        return questionHolderList;
    }

    public void setQuestionHolderList(List<QuestionHolder> questionHolderList) {
        this.questionHolderList = questionHolderList;
    }

    public interface OnGridClickedListener{
        public void OnGridClicked(int position );
    }
    public void setOnItemClickedListener (nCardAdapter.OnGridClickedListener listener){
        this.Listener = listener;
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


    public void newshowite(Context context, Uri uri, ImageView imageView) {

        Glide.with(context /* context */)
                .load(uri)
                .into(imageView)
                .onResourceReady(imageView.getDrawable(), new Transition<Drawable>() {
                    @Override
                    public boolean transition(Drawable current, Transition.ViewAdapter adapter) {

                        return false;
                    }
                });

    }
}






