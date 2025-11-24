package com;

import com.javapractice.FileUtils;
import com.javapractice.Java8WatchServiceExample;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileAPITest {
    private static final String HOME = "D:\\Personal\\Projects\\JavaPractice\\NewFolder";
    private static final String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {

        // check file exists
        Path homePath = Paths.get(HOME);
        Assert.assertTrue(Files.exists(homePath));

        // delete file and check if not exist
        Path playPath = Paths.get(HOME + "\\" + PLAY_WITH_NIO);
        if (Files.exists(playPath)) FileUtils.deleteFiles(playPath.toFile());
        Assert.assertTrue(Files.notExists(playPath));

        // create directory
        Files.createDirectory(playPath);
        Assert.assertTrue(Files.exists(playPath));

        // create file
        IntStream.range(1, 10).forEach(counter -> {
            Path tempFile = Paths.get(playPath + "\\temp" + counter);
            Assert.assertTrue(Files.notExists(tempFile));
            try {Files.createFile(tempFile);} catch (IOException ignored) {}
            Assert.assertTrue(Files.exists(tempFile));
        });

        // list files, directories, and files with extensions
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() &&
                                                        path.toString().startsWith("temp"))
                .forEach(System.out::println);
    }

    @Test
    public void givenDirectoryWhenWatchedListAllTheActivities() throws IOException {
        Path dir = Paths.get(HOME + "\\" + PLAY_WITH_NIO);
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        new Java8WatchServiceExample(dir).processEvents();
    }
}
