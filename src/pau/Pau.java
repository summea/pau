package pau;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class Pau {
    public static void main(String[] args) {
        //Path path = Paths.get("/pau/src/data/data.txt");
        Path path = FileSystems.getDefault().getPath("src/data/data.txt");
        try {
            List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
            
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
