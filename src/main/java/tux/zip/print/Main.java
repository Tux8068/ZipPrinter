package tux.zip.print;

import tux.zip.print.util.MiscUtil;
import tux.zip.print.util.StringUtil;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static Thread thread;
    private static File file = new File("config.txt");

    public static void main(String[] args) {
        thread = new Thread(Main::run);
        thread.start();
    }

    private static void run() {
        System.out.println(StringUtil.PREFIX + "Welcome!");
        System.out.println(StringUtil.PREFIX + "Finding config.");

        BufferedWriter br;

        try {
            if (!file.exists() || checkIfEmpty()) {
                br = new BufferedWriter(new FileWriter("config.txt"));
                br.write("Replace this with the text you'd like to print to file:\nExample:\nThanks to bob & george\nfor making this possible.");
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println(StringUtil.PREFIX + "Enter filename. (the file has to be in same directory).");
        String dir = sc.nextLine();

        try {
            System.out.println(StringUtil.PREFIX + "Printing to zip file: " + MiscUtil.readAll(new File("config.txt")));
            MiscUtil.writeComment(new File(dir), MiscUtil.readAll(new File("config.txt")), 8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkIfEmpty() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("config.txt"));
        return br.readLine() == null;
    }
}
