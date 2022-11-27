package com.java;


import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class EmployeePayRollTest {
    private static String HOME =System.getProperty("user.home"); // cd ~ gives home directory
    private static String PLAYGROUND = "PlayGround";

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException{

        //checking whether file exists
        Path homePath= Paths.get(HOME);
        Assert.assertTrue(Files.exists((homePath)));

        //delete file and check file not exist
        Path playPath=Paths.get(HOME+"/"+PLAYGROUND);
        if(Files.exists(playPath))FileUtils.deleteFiles(playPath.toFile());
        Assert.assertTrue(Files.notExists(playPath));

        //create directory
        Files.createDirectory(playPath);
        Assert.assertTrue(Files.exists(playPath));

        //create file

        IntStream.range(1,10).forEach(cntr->{
            Path tempFile=Paths.get(playPath+"/temp"+cntr);
            Assert.assertTrue(Files.notExists(tempFile));
            try{
                Files.createFile(tempFile); }
            catch (IOException e){
                Assert.assertTrue(Files.exists(tempFile));
            }
        });

    }


}
