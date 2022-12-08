package com.Snake.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Leaderboard {

    public static ArrayList<String> playernameList = new ArrayList<>();
    public static ArrayList<Integer> playerscoreList = new ArrayList<>();

    public void WriteLeaderboardFile(String playerName, int playerScore){
        try {
            FileWriter fw = new FileWriter("textfiles/leaderboard.txt", true);
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

    public void ReadLeaderboardFile() {
        int i = 0;
        try {
            File fn = new File("textfiles/leaderboard.txt");
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
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
