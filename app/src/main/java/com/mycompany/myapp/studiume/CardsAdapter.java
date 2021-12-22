package com.mycompany.myapp.studiume;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CardsAdapter extends BaseAdapter{
    private Activity activity;
    private final static int AVATAR_WIDTH = 150;
    private final static int AVATAR_HEIGHT = 300;
    private List<QuestionHolder> data;

    public CardsAdapter(Activity activity, List<QuestionHolder> data) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public List<QuestionHolder> getData() {
        return data;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public Object getItem(int position) {
        return null;
    }

    public void setData(List<QuestionHolder> data) {
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.single_question_view, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        QuestionHolder questionHolder = data.get(position);
        //setting data to views
        holder.question_body.setText(questionHolder.getQuestion_body());
        holder.answer_body.setText(questionHolder.getAnswer_body());

        if(questionHolder.getType()==HomeActivity.OBJECTIVES) {
            holder.lin_options.setVisibility(View.VISIBLE);
            holder.options_a.setText(String.valueOf(questionHolder.getOptions_a()));
            holder.options_b.setText(String.valueOf(questionHolder.getOptions_b()));
            holder.options_c.setText(String.valueOf(questionHolder.getOptions_c()));
            holder.options_d.setText(String.valueOf(questionHolder.getOptions_d()));
            holder.options_e.setText(String.valueOf(questionHolder.getOptions_e()));
        }
        if(questionHolder.isHas_answer_image()) {
            holder.answer_image.setImageResource(R.drawable.ann);
        }
        if(questionHolder.isHas_question_image()) {
            holder.question_image.setImageResource(R.drawable.checkedgreen);
        }
//        holder.location.setText(getItem(position).getLocation());
//        holder.avatar.setImageBitmap(decodeSampledBitmapFromResource(activity.getResources(),
//                getItem(position).getDrawableId(), AVATAR_WIDTH, AVATAR_HEIGHT));

        return convertView;
    }

    private class ViewHolder{
        private ImageView question_image, answer_image;
        private TextView question_body,options_a, options_b, options_c, options_d, options_e;
        private TextView answer_body, say_option;
        LinearLayout lin_options;

        public ViewHolder(View view) {
            question_body = (TextView)view.findViewById(R.id.questionet);
            answer_body = (TextView)view.findViewById(R.id.answer);
            options_a = (TextView)view.findViewById(R.id.options_a);
            options_b = (TextView)view.findViewById(R.id.options_b);
            options_c = (TextView)view.findViewById(R.id.options_c);
            options_d = (TextView)view.findViewById(R.id.options_d);
            options_e = (TextView)view.findViewById(R.id.options_e);
            say_option = (TextView)view.findViewById(R.id.say_option);
            lin_options = (LinearLayout)view.findViewById(R.id.lin_options);

            question_image = (ImageView)view.findViewById(R.id.question_image);
            answer_image = (ImageView)view.findViewById(R.id.answer_image);

        }
    }


//    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
//
//        // First decode with inJustDecodeBounds=true to check dimensionsfinal BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res, resId, options);
//
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeResource(res, resId, options);
//    }


    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}


