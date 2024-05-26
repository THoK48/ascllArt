package org.kth.standard;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

    public static int getT(String ch) {

        Canvas c = new Canvas() {
            public void paint(Graphics g) {
                g.setColor(Color.BLACK);
                g.drawString(ch, 32, 32);
            }
        };

        BufferedImage newIm = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = newIm.getGraphics();
        c.paint(g);

        int output = 0;
        for (int y = 0; y < 32; y++) {
            for (int x = 0; x < 32; x++) {
                if (newIm.getRGB(x, y) < 0) {
                    output++;
                }

            }
        }
        System.out.println();
        return output;

    }

    public static void stringGetter() {
        System.out.println("Start\n");

        String[] chars = new String[1024];

        for (int i = '가'; i < '?'; i++) {
            int k = getT(String.valueOf((char) i));
            chars[k] = String.valueOf((char) i);
        }
        for (int i = 32; i < 'z' + 4; i++) {
            int k = getT(String.valueOf((char) i + "" + (char) i));
            chars[k] = String.valueOf((char) i + "" + (char) i);
        }
        for (int i = 0; i < 67; i++) {
            String k;

            if (chars[i] != null) {
                k = chars[i];
            } else {
                chars[i] = chars[i - 1];
                k = chars[i];
            }
            System.out.println("\"" + k + "\",");
        }

    }

    static String[] strings = { "  ", "  ", "..", "..", "``", "``", "--", "--", ";;", ";;", ";;", ";;", "\"\"",
            "\"\"", "__", "__", "ii", "ii", "rr", "rr", "jj", "스", "}}", "수", "vv", "슈", "xx", "즈", "yy", "주", "zz",
            "UU", "uu", "추", "kk", "mm", "hh", "HH", "KK", "ww", "qq", "NN", "99", "DD", "SS", "?", "gg", "?", "RR",
            "?", "$$", "?", "?", "?", "BB", "MM", "##", "?", "?", "?", "?", "?", "@@", "?", "?", "?", "?", "?" };

    public static String getString(double d) {
        int k = (int) (67 - (67 / 255.0) * d);
        return strings[k];
    }


    public static void a () throws IOException {

        BufferedImage im = ImageIO.read(new File("C:\\Users\\taeho\\OneDrive\\바탕 화면\\공부한거\\imageTest\\frames\\frame_0001.jpg"));


        int size = 4;
        double[][] map = new double[im.getHeight() / size - 1][im.getWidth() / size - 1];
        {

            for (int y = 0; y < map.length; y += 1) {
                for (int x = 0; x < map[y].length; x += 1) {
                    int k = 0;
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {

                            Color c = new Color(im.getRGB(x * size + j, y * size + i));
                            map[y][x] += c.getRed() / 3.0;
                            map[y][x] += c.getGreen() / 3.0;
                            map[y][x] += c.getBlue() / 3.0;
                            k++;
                        }

                    }
                    map[y][x] /= k;
                }
            }
        }
        String[][] convert = new String[map.length][map[0].length];
        {
            for (int i = 0; i < convert.length; i++) {
                for (int j = 0; j < convert[i].length; j++) {
                    convert[i][j] = getString(map[i][j]);
                }
            }
        }
        String filePath = "C:\\Users\\taeho\\OneDrive\\바탕 화면\\공부한거\\imageTest\\출력.txt";
        FileWriter fw = new FileWriter(new File(filePath));


        for(String[] chars:convert)
        {
            for (String chr : chars) {
                fw.write(chr);
            }
            fw.write("\r\n");
        }fw.close();

        Runtime.getRuntime().exec("notepad \""+filePath+ "\"");

    }

    public static void main(String[] args) throws IOException {
        a();
    }
}
