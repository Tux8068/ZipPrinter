package tux.zip.print.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipOutputStream;

public class MiscUtil {

    public static String readAll(File file) throws IOException {
        char[] buffer = new char[1 << 16];

        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            int count = reader.read(buffer, 0, buffer.length);
            if (count > 0)
                content.append(buffer, 0, count);
        }

        reader.close();

        return content.toString();
    }

    public static String load(File file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String s = "";
            while (true) {
                String s1 = reader.readLine();
                if (s1 == null)
                    break;
                s += s1;
            }
            reader.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeComment(File output, String comment, int level) {
        try {
            FileOutputStream fos = new FileOutputStream(output);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.setComment(comment);
            zos.flush();
            zos.finish();
            zos.close();

        } catch (Exception e) {
            System.out.println("Failed to write.");
        }
    }
}

