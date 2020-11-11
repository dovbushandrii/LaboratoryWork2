package appPack;

import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static Pair<ArrayList<SingleNote>, ArrayList<SingleNote>> readFile(String nameOfFile) throws Exception {
        ArrayList<SingleNote> active = new ArrayList<>();
        ArrayList<SingleNote> archive = new ArrayList<>();
        File readFile = new File(nameOfFile);
        if (readFile.exists()) {
            Scanner scanner = new Scanner(readFile);
            Integer activeSize = scanner.nextInt();
            Integer archiveSize = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < activeSize; i++) {
                String date = scanner.nextLine();
                String note = scanner.nextLine();
                String type = scanner.nextLine();
                active.add(new SingleNote(date, note, ContextEnum.valueOf(type)));
            }
            for (int i = 0; i < archiveSize; i++) {
                String date = scanner.nextLine();
                String note = scanner.nextLine();
                String type = scanner.nextLine();
                archive.add(new SingleNote(date, note, ContextEnum.valueOf(type)));
            }
        }
        return new Pair<>(active, archive);
    }

    public static void writeToFile(String nameOfFile, Pair<ArrayList<SingleNote>, ArrayList<SingleNote>> activeAndArchive) throws Exception {
        ArrayList<SingleNote> active = activeAndArchive.getKey();
        ArrayList<SingleNote> archive = activeAndArchive.getValue();
        FileWriter writer = new FileWriter(nameOfFile);
        Integer activeSize = active.size();
        Integer archiveSize = archive.size();
        writer.write(activeSize.toString());
        writer.write('\n');
        writer.write(archiveSize.toString());
        writer.write('\n');
        for (SingleNote singleNote : active) {
            String date = singleNote.getTimeString() + "\n";
            String note = singleNote.getNoteProperty().get() + "\n";
            String type = singleNote.getConType() + "\n";
            writer.write(date);
            writer.write(note);
            writer.write(type);
        }
        for (SingleNote singleNote : archive) {
            String date = singleNote.getTimeString() + "\n";
            String note = singleNote.getNoteProperty().get() + "\n";
            String type = singleNote.getConType() + "\n";
            writer.write(date);
            writer.write(note);
            writer.write(type);
        }
        writer.close();

    }
}
