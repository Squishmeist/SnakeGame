package com.Snake.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Project COMP2013-Coursework
 * @Description Leaderboard Class
 * @Author Ainsley Lee
 */

public class Leaderboard {

    public static ArrayList<String> playernameList = new ArrayList<>();
    public static ArrayList<Integer> playerscoreList = new ArrayList<>();

    /**
     * Method stores the players name and score to the specified text file.
     * This is done by appending the text file with the variable playerName
     * then adding a line, then adding the playerScore variable and another line.
     *
     * @param playerName variable set to the players inputted name in the StartSceneController class
     * @param playerScore variable set to the players score in the GameSceneController class
     */
    public void WriteLeaderboardFile(String playerName, int playerScore){
        try {
            FileWriter fw = new FileWriter("src/main/resources/com/Snake/textfiles/leaderboard.txt", true);
            fw.write(playerName);
            fw.write("\n");
            fw.write(playerScore + "");
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method reads the specified text file and stores alternate lines to array lists.
     * All even lines are stored to the playernameList and odd lines stored to the playerscoreList.
     */
    public void ReadLeaderboardFile() {
        int i = 0;
        try {
            File fn = new File("src/main/resources/com/Snake/textfiles/leaderboard.txt");
            Scanner fs = new Scanner(fn);
            while (fs.hasNextLine()) {
                String data = fs.nextLine();
                if(i % 2 == 0) {
                    playernameList.add(data);
                }else{
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
