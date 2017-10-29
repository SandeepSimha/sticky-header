package com.support.my.sticky_header;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.support.my.sticky_header.lib.StickyHeadersItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DiningMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adapter = new TheatreAdapter();
        adapter = new DiningMenuAdapter(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dining_menu_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new StickyHeadersItemDecoration(adapter));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //adapter.setMenu(prepareTheatreData());
        adapter.setMenu(prepareTheatreData());
    }

    private DiningMenu prepareTheatreData() {
        DiningMenu diningMenu = new DiningMenu();

        List<DiningMenuHeader> viewMenuHeaderList = new ArrayList<>();
        List<DiningMenuElement> viewMenuInfoList = new ArrayList<>();
        List<DiningMenuElement> viewMenuInfoList2 = new ArrayList<>();
        List<DiningMenuElement> viewMenuInfoList3 = new ArrayList<>();
        List<DiningMenuElement> viewMenuInfoList4 = new ArrayList<>();
        List<Integer> legendIconList = new ArrayList<>();
        List<Integer> legendIconList2 = new ArrayList<>();
        List<Integer> legendIconList3 = new ArrayList<>();

        DiningMenuHeader diningMenuHeader = new DiningMenuHeader();
        diningMenuHeader.setMenuType("Appetizers");

        DiningMenuHeader diningMenuHeader2 = new DiningMenuHeader();
        diningMenuHeader2.setMenuType("Soups & Salads");

        DiningMenuHeader diningMenuHeader3 = new DiningMenuHeader();
        diningMenuHeader3.setMenuType("Specialty Cocktails");

        DiningMenuHeader diningMenuHeader4 = new DiningMenuHeader();
        diningMenuHeader4.setMenuType("Bread Service");

        DiningMenuHeader diningMenuHeader5 = new DiningMenuHeader();
        diningMenuHeader5.setMenuType("Main Course");

        DiningMenuHeader diningMenuHeader6 = new DiningMenuHeader();
        diningMenuHeader6.setMenuType("Vegetrain");

        DiningMenuHeader diningMenuHeader7 = new DiningMenuHeader();
        diningMenuHeader7.setMenuType("Lighter Note Offerings");

        DiningMenuElement diningMenuElement = new DiningMenuElement();
        diningMenuElement.setTitle("Cucumber Garden Roll");
        diningMenuElement.setDescription("Filled with Julienne of Carrots, Bell Peppers and Zucchini, flavored with Cilantro and dressed with White Shoyu and Lemon Dressing");
        diningMenuElement.setLegendIconList(legendIconList);

        DiningMenuElement diningMenuElement2 = new DiningMenuElement();
        diningMenuElement2.setTitle("Ahi Tuna and Avocado Tower");
        diningMenuElement2.setDescription("With Crispy Noodles and Wasabi Dressing");
        diningMenuElement2.setLegendIconList(legendIconList2);

        DiningMenuElement diningMenuElement3 = new DiningMenuElement();
        diningMenuElement3.setTitle("Breaded Turkey Breast");
        diningMenuElement3.setDescription("with Tomato Sauce. All main courses served with fresh Vegetables, smashed Potatoes or Steak Fries");
        diningMenuElement3.setLegendIconList(legendIconList3);

        DiningMenuElement diningMenuElement4 = new DiningMenuElement();
        diningMenuElement4.setTitle("Margherita Pizza");
        diningMenuElement4.setDescription("Baked with Mozzarella Cheese and Tomato Sauce.");

        DiningMenuElement diningMenuElement5 = new DiningMenuElement();
        diningMenuElement5.setTitle("Applewood Smoked Bacon Wild Mushroom Tart");
        diningMenuElement5.setDescription("With Creamy Leeks");

        DiningMenuElement diningMenuElement6 = new DiningMenuElement();
        diningMenuElement6.setTitle("Cheese Pizza");
        diningMenuElement6.setDescription("Baked with Mozzarella Cheese and Tomato Sauce.");
        diningMenuElement6.setLegendIconList(legendIconList3);

        DiningMenuElement diningMenuElement7 = new DiningMenuElement();
        diningMenuElement7.setTitle("Mixed Garden Salad");
        diningMenuElement7.setDescription("Mixed Leaves with Tomatoes, Cucumber, Carrot, Peppers, Red Onions with a choice of dressings (Ranch, Raspberry, Italian or Balsamic)");
        diningMenuElement7.setLegendIconList(legendIconList2);

        DiningMenuElement diningMenuElement8 = new DiningMenuElement();
        diningMenuElement8.setTitle("Full Wine List and Cocktail List");
        diningMenuElement8.setDescription("Please call In-Stateroom Dining for offerings and prices.");

        DiningMenuElement diningMenuElement9 = new DiningMenuElement();
        diningMenuElement9.setTitle("Margherita Pizza");
        diningMenuElement9.setDescription("Baked with Mozzarella Cheese and Tomato Sauce.");
        diningMenuElement9.setLegendIconList(legendIconList);

        viewMenuInfoList.add(diningMenuElement);
        viewMenuInfoList.add(diningMenuElement2);
        viewMenuInfoList.add(diningMenuElement3);

        viewMenuInfoList2.add(diningMenuElement4);
        viewMenuInfoList2.add(diningMenuElement5);
        viewMenuInfoList2.add(diningMenuElement6);
        viewMenuInfoList2.add(diningMenuElement7);

        viewMenuInfoList3.add(diningMenuElement7);

        viewMenuInfoList4.add(diningMenuElement8);
        viewMenuInfoList4.add(diningMenuElement9);

        diningMenuHeader.setDiningMenuElements(viewMenuInfoList);
        diningMenuHeader2.setDiningMenuElements(viewMenuInfoList2);
        diningMenuHeader3.setDiningMenuElements(viewMenuInfoList3);
        diningMenuHeader4.setDiningMenuElements(viewMenuInfoList4);

        viewMenuHeaderList.add(diningMenuHeader);
        viewMenuHeaderList.add(diningMenuHeader2);
        viewMenuHeaderList.add(diningMenuHeader3);
        viewMenuHeaderList.add(diningMenuHeader4);

        diningMenu.setHeaders(viewMenuHeaderList);

        return diningMenu;
    }

    /*private List<TheatreHeader> prepareTheatreData() {

        List<TheatreHeader> theatreHeaderList = new ArrayList<>();
        List<MovieElement> movieMenuInfoList = new ArrayList<>();
        List<MovieElement> movieMenuInfoList2 = new ArrayList<>();
        List<MovieElement> movieMenuInfoList3 = new ArrayList<>();
        List<MovieElement> movieMenuInfoList4 = new ArrayList<>();

        TheatreHeader diningMenuHeader = new TheatreHeader();
        diningMenuHeader.setName("Appetizers");

        TheatreHeader diningMenuHeader2 = new TheatreHeader();
        diningMenuHeader2.setName("Soups & Salads");

        TheatreHeader diningMenuHeader3 = new TheatreHeader();
        diningMenuHeader3.setName("Specialty");

        TheatreHeader diningMenuHeader4 = new TheatreHeader();
        diningMenuHeader4.setName("Cocktails");

        TheatreHeader diningMenuHeader5 = new TheatreHeader();
        diningMenuHeader5.setName("Specialty Cocktails");

        MovieElement movie = new MovieElement("Mad Max: Fury Road", "Action & Adventure", "2015");
        MovieElement movie2 = new MovieElement("Inside Out", "Animation, Kids & Family", "2015");
        MovieElement movie3 = new MovieElement("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        MovieElement movie4 = new MovieElement("Shaun the Sheep", "Animation", "2015");
        MovieElement movie5 = new MovieElement("The Martian", "Science Fiction & Fantasy", "2015");
        MovieElement movie6 = new MovieElement("Mission: Impossible Rogue Nation", "Action", "2015");
        MovieElement movie7 = new MovieElement("Up", "Animation", "2009");
        MovieElement movie8 = new MovieElement("Star Trek", "Science Fiction", "2009");
        MovieElement movie9 = new MovieElement("The LEGO Movie", "Animation", "2014");

        movieMenuInfoList.add(movie);
        movieMenuInfoList.add(movie2);
        movieMenuInfoList.add(movie3);
        movieMenuInfoList.add(movie4);
        movieMenuInfoList.add(movie5);
        movieMenuInfoList.add(movie6);
        movieMenuInfoList.add(movie7);
        movieMenuInfoList.add(movie8);

        movieMenuInfoList2.add(movie6);
        movieMenuInfoList2.add(movie7);
        movieMenuInfoList2.add(movie8);
        movieMenuInfoList2.add(movie9);
        movieMenuInfoList2.add(movie);
        movieMenuInfoList2.add(movie2);
        movieMenuInfoList2.add(movie3);

        movieMenuInfoList3.add(movie5);
        movieMenuInfoList3.add(movie6);
        movieMenuInfoList3.add(movie7);
        movieMenuInfoList3.add(movie4);
        movieMenuInfoList3.add(movie6);
        movieMenuInfoList3.add(movie2);
        movieMenuInfoList3.add(movie);

        movieMenuInfoList4.add(movie3);
        movieMenuInfoList4.add(movie2);
        movieMenuInfoList4.add(movie5);
        movieMenuInfoList4.add(movie4);
        movieMenuInfoList4.add(movie6);
        movieMenuInfoList4.add(movie9);

        diningMenuHeader.setMovieElements(movieMenuInfoList);
        diningMenuHeader2.setMovieElements(movieMenuInfoList3);
        diningMenuHeader3.setMovieElements(movieMenuInfoList4);
        diningMenuHeader4.setMovieElements(movieMenuInfoList2);
        diningMenuHeader5.setMovieElements(movieMenuInfoList3);

        theatreHeaderList.add(diningMenuHeader);
        theatreHeaderList.add(diningMenuHeader2);
        theatreHeaderList.add(diningMenuHeader3);
        theatreHeaderList.add(diningMenuHeader4);
        theatreHeaderList.add(diningMenuHeader5);

        return theatreHeaderList;
    }*/
}
