import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к папке, которую хотите скопировать");
        String from = scanner.nextLine();
        System.out.println("Введите путь к папке, в которую хотите скопировать содержимое");
        String to = scanner.nextLine();
        try {
            copyDir(from, to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyDir(String from, String to) throws IOException {
        File folder = new File(from);
        File[] listOfFiles = folder.listFiles();
        Path destDir = Paths.get(to);
        if (!destDir.toFile().isDirectory()) {
            Files.createDirectory(destDir);
        }
        if (listOfFiles != null) {
            for (File file : listOfFiles)
                if (file.isDirectory()) {
                    copyDir(file.getAbsolutePath(), destDir.resolve(file.getName()).toString());
                } else
                    Files.copy(file.toPath(), destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
