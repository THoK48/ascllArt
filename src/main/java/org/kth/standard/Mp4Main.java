package org.kth.standard;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Mp4Main {

    public static void main(String[] arg){
        String videoPath = "\"C:\\Users\\taeho\\OneDrive\\바탕 화면\\공부한거\\imageTest\\Hoshino.mp4\"";
        String outputDirectory = "\"C:\\Users\\taeho\\OneDrive\\바탕 화면\\공부한거\\imageTest\\frames\""; // 출력 디렉토리 경로
        String outputPattern = "frame_%04d.jpg";

        String command = String.format("ffmpeg -i %s -vf fps=1 -q:v 2 %s/%s", videoPath, outputDirectory, outputPattern);

        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
