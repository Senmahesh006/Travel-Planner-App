package com.example.travel_planer.Activites;

import android.app.Application;

import com.example.travel_planer.Activites.Domain.IteamDomain;

import java.util.ArrayList;
import java.util.Arrays;

public class MyIteams extends Application {


    public static ArrayList<IteamDomain> Globles_iteams = new ArrayList<IteamDomain>();


    public MyIteams() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        IteamDomain I1 = new IteamDomain("Goa", "Goa", "Goa (Konkani: [goa] i) is a state on the southwestern coast of India within the Konkan region," +
                " geographically separated from the Deccan highlands by the Western Ghats." +
                "[6][7] It is bound by the Indian states of Maharashtra to the north, and Karnataka to the east and south, with the Arabian Sea in the west. " +
                "It is India's smallest state by area and fourth-smallest by population. Goa has the highest GDP per capita among all Indian states," +
                "[3][8] two and a half times as high as the GDP per capita of the country as a whole.[9] The Eleventh Finance Commission of India named Goa the best-placed state because of its infrastructure," +
                " and India's National Commission on Population rated it as having the best quality of life in India (based on the commission's \"12 Indicators\")." +
                "[9] It is the third-highest ranking among Indian states in the human development index.[4]", 2, true, 2.2, "goa", true, 2000);

        IteamDomain I2 = new IteamDomain("Camping", "Sierra Nevada National Park (Spain)", "Camping is a form of outdoor recreation or outdoor education involving overnight stays with a basic temporary shelter such as a tent. Camping can also include a recreational vehicle," +
                " sheltered cabins, a permanent tent, a shelter such as a bivy or tarp, or no shelter at all. " +
                "Typically, participants leave developed areas to spend time outdoors," +
                " in pursuit of activities providing them enjoyment or an educational experience. " +
                "Spending the night away from home distinguishes camping from day-tripping, picnicking, and other outdoor activities.", 0, true, 5.0, "camp", false, 3000);
        IteamDomain I3 = new IteamDomain("Forest", " Forest (disambiguation).", "A forest is an area of land dominated by trees.[1] Hundreds of definitions of forest are used throughout the world, " +
                "incorporating factors such as tree density, tree height, land use, legal standing, and ecological function.[2][3][4] The United Nations' Food and Agriculture Organization (FAO) defines a forest as," +
                " \"Land spanning more than 0.5 hectares with trees higher than 5 meters and a canopy cover of more than 10 percent, or trees able to reach these thresholds in situ. It does not include land that is predominantly under agricultural or urban use." +
                "\"[5] Using this definition, Global Forest Resources Assessment 2020 (FRA 2020) found that forests covered 4.06 billion hectares (10.0 billion acres; 40.6 million square kilometres;" +
                " 15.7 million square miles), or approximately 31 percent of the world's land area in 2020.[6]", 0, true, 4.1, "forest", false, 4000);
        IteamDomain I4 = new IteamDomain("Desert", "jaisalmer", "A desert is a barren area of landscape where little precipitation occurs and, consequently, " +
                "living conditions are hostile for plant and animal life. The lack of vegetation exposes the unprotected surface of the ground to denudation. About one-third of the land surface of the Earth is arid or semi-arid." +
                " This includes much of the polar regions, where little precipitation occurs, and which are sometimes called polar deserts or \"cold deserts\". " +
                "Deserts can be classified by the amount of precipitation that falls," +
                " by the temperature that prevails, by the causes of desertification or by their geographical location.[1]", 2, true, 5.0, "desert", false, 6000);
        IteamDomain I5 = new IteamDomain("Mountain", "Mount Everest, Earth's highest mountain", "A mountain is an elevated portion of the Earth's crust, generally with steep sides that show significant exposed bedrock. " +
                "Although definitions vary, a mountain may differ from a plateau in having a limited summit area, and is usually higher than a hill, " +
                "typically rising at least 300 metres (980 ft) above the surrounding land. " +
                "A few mountains are isolated summits, but most occur in mountain ranges.[1]", 2, true, 5.0, "mounatin", false, 10000);


        Globles_iteams.addAll(Arrays.asList(new IteamDomain[]{I1, I2, I3, I4, I5}));


    }

    public static ArrayList<IteamDomain> getGlobles_iteams() {
        return Globles_iteams;
    }

    public static void setGlobles_iteams(ArrayList<IteamDomain> globles_iteams) {
        MyIteams.Globles_iteams = globles_iteams;
    }
}


