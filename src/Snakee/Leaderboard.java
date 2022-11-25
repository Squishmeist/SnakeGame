package Snakee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

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

    public static void ReadLeaderboardFile() {
        int i = 0;
        LinkedList<String> playernameList = new LinkedList<String>();
        LinkedList<Integer> playerscoreList = new LinkedList<Integer>();

        try {
            File fn = new File("leaderboard.txt");
            Scanner fs = new Scanner(fn);
            System.out.println("READ FROM FILE");
            while (fs.hasNextLine()) {
                String data = fs.nextLine();
                if(i % 2 == 0)
                {
                    playernameList.add(data);
                } else{
                    playerscoreList.add(Integer.parseInt(data));
                }
                i++;
            }
            System.out.println("LinkedList Name : " + playernameList);
            System.out.println("LinkedList Score : " + playerscoreList);
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
