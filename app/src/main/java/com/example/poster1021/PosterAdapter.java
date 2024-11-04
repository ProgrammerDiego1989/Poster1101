//import package
package com.example.poster1021;

//import statements
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.makeramen.roundedimageview.RoundedImageView;
import java.util.ArrayList;
import java.util.List;

//Create method for PosterAdapter use recursive ViewAdapter to handle holders and to insert data
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder>{
    @NonNull
    @Override //implement PosterViewHolder method this displays everything in ItemView poster
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    @Override //implement onBindViewHolder method
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPoster(posterList.get(position));
    }

    @Override //implement getItemCount method
    public int getItemCount() {
        return posterList.size();
    }

    //define posterList
    private List<Poster> posterList;


    //constructors for posterList and postersListener
    public PosterAdapter(List<Poster> posterList, PosterListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    //define postersListener
    private PosterListener postersListener;

    //implement getSelectedPosters method for selecting posters
    public List<Poster> getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList){
            if(poster.isSelected){
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }


    //method that uses posterViewHolder and extends it to RecyclerView.ViewHolder
    class PosterViewHolder extends RecyclerView.ViewHolder{

        //define ConstraintLayout attribute
        ConstraintLayout layoutPosters;
        // define viewBackground attribute
        View viewBackground;
        RoundedImageView imagePoster;
        // define textName, textCreatedBy, textStory attribute
        TextView textName, textCreatedBy, textStory;
        // define ratingBar attribute
        RatingBar ratingBar;
        // define imageSelected attribute
        ImageView imageSelected;


        //create constructor for PosterViewHolder function
        //connect functions to xml code
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutposters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        //create bindPoster method to fit in poster data
        void bindPoster(final Poster poster) {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            //create function for poster if selected
            if(poster.isSelected){
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else{
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            //create function for onclick selection and unselected posters
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if (getSelectedPosters().isEmpty()) {
                            postersListener.onPosterAction(false);
                        }
                    } else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }


                }

            });
        }

    }
}
