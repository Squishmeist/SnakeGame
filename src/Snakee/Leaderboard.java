package Snakee;

import java.io.FileWriter;
import java.io.IOException;

public class Leaderboard {

    public static void WriteLeaderboardFile(){
        try {
            FileWriter fw = new FileWriter("leaderboard.txt", true);

            fw.write("\n");
            fw.write("WRITE TO FILE");
            fw.close();

            System.out.println("WRITTEN TO FILE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
