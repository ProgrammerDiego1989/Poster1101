//import packages
package com.example.poster1021;
//import statements
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//Main method class MainActivity that extends to AppCompatActivity
public class MainActivity extends AppCompatActivity implements PosterListener  {

    //define Button attribute
    private Button buttonAddToWatchlist;

    //create method to
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //display postersRecyclerView information
        RecyclerView postersRecyclerView = findViewById(R.id.postersRecyclerView);
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchList);

        //prepare data
        List<Poster> posterList = new ArrayList<>();

        //display first movie
        Poster BulletTrain = new Poster();
        BulletTrain.image = R.drawable.posterimage1;
        BulletTrain.name = "Bullet Train";
        BulletTrain.createBy = "Antoine Fuqua";
        BulletTrain.rating = 4f;
        BulletTrain.story = "A trained skilled agent on a mission to save his wife";
        posterList.add(BulletTrain);

        //display second movie
        Poster Pearl = new Poster();
        Pearl.image = R.drawable.posterimage2;
        Pearl.name = "Pearl";
        Pearl.createBy = "Ti West";
        Pearl.rating = 3f;
        Pearl.story = "A young hollywood actress staring in a drama and horror intensive movie";
        posterList.add(Pearl);

        //display third movie
        Poster TheFlash = new Poster();
        TheFlash.image = R.drawable.posterimage3;
        TheFlash.name = "The Flash ";
        TheFlash.createBy = "Andres Muschietti";
        TheFlash.rating = 4f;
        TheFlash.story = "A origin story of the DC superhero Flash ";
        posterList.add(TheFlash);

        //display fourth movie
        Poster HarryPotter = new Poster();
        HarryPotter.image = R.drawable.posterimage4;
        HarryPotter.name = "Harry Potter and the Sorcerer's Stone";
        HarryPotter.createBy = "Chris Columbus";
        HarryPotter.rating = 4f;
        HarryPotter.story = "Award winning Sci-fi movie starting Daniel Radcliffe and many more";
        posterList.add(HarryPotter);

        //display fifth movie
        Poster StarWars = new Poster();
        StarWars.image = R.drawable.posterimage5;
        StarWars.name = "Star Wars: Episode I-The Phantom Menace";
        StarWars.createBy = "George Lucas";
        StarWars.rating = 4f;
        StarWars.story = "Sci-fi movie containing battles of epic star wars for ultimate rule";
        posterList.add(StarWars);

        //display sixth movie
        Poster BeetleJuiceMovie = new Poster();
        BeetleJuiceMovie.image = R.drawable.posterimage6;
        BeetleJuiceMovie.name = "BeetleJuice BeetleJuice";
        BeetleJuiceMovie.createBy = "Micheal McDowell";
        BeetleJuiceMovie.rating = 4.8f;
        BeetleJuiceMovie.story = "Hollywood classic now with a sequel entailing comedy and a trill";
        posterList.add(BeetleJuiceMovie);

        //display seventh movie
        Poster TheHobbit = new Poster();
        TheHobbit.image = R.drawable.posterimage7;
        TheHobbit.name = "The Hobbit";
        TheHobbit.createBy = "Peter Jackson";
        TheHobbit.rating = 5f;
        TheHobbit.story = "A little man goes on a breathtaking journey to save the world";
        posterList.add(TheHobbit);

        //display eighth movie
        Poster TheJoker = new Poster();
        TheJoker.image = R.drawable.posterimage8;
        TheJoker.name = "The Joker";
        TheJoker.createBy = "Todd Phillips";
        TheJoker.rating = 4f;
        TheJoker.story = "A origin story of the infamous DC villain in Gotham City ";
        posterList.add(TheJoker);

        //display ninth movie
        Poster TheAvengers = new Poster();
        TheAvengers.image = R.drawable.posterimage9;
        TheAvengers.name = "Avengers: Endgame ";
        TheAvengers.createBy = "Joe Russo and Anthony Russo ";
        TheAvengers.rating = 4f;
        TheAvengers.story = "Infamous Marvel superheroes on a mission to save the world";
        posterList.add(TheAvengers);

        //display tenth movie (my most favorite)
        Poster SpiritedAway = new Poster();
        SpiritedAway.image = R.drawable.posterimage10;
        SpiritedAway.name = "Spirited Away";
        SpiritedAway.createBy = "Hayao Miyazaki";
        SpiritedAway.rating = 5f;
        SpiritedAway.story = "Award winning Japanese animation film of a young girl on a journey";
        posterList.add(SpiritedAway);


        //call information and feed it to PosterAdapter
        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

        //create onclick function for posters
        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();

                //create new StringBuilder and implement algorithm
                StringBuilder posterNames = new StringBuilder();
                for (int i = 0; i < selectPosters.size(); i++){
                    if (i == 0){
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this,posterNames.toString(),Toast.LENGTH_SHORT).show();
            }

        });

    }

    //implement onPosterAction
    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}