import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Gvozd on 09.01.2016.
 */
public class FileInput {
    private static String text = "This new text \nThis new text2\nThis new text3\nThis new text4\n";
    private static String fileName = "c:\\a.txt";

    public static void main(String[] args) throws FileNotFoundException {
        write(fileName, text);
    }

    public static void write(String fileName, String data){
        File file = new File(fileName);

        try {
            if (!file.exists()){
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(data);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
