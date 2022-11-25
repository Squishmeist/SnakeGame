package Snakee;

import java.io.FileWriter;
import java.io.IOException;

public class Leaderboard {

    public static void WriteLeaderboardFile(String playerName, int playerScore){
        try {
            FileWriter fw = new FileWriter("leaderboard.txt", true);

            fw.write(playerName);
            fw.write("\n");
            fw.write(playerScore + "");
            fw.write("\n");
            fw.close();

            System.out.println("WRITTEN TO FILE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
