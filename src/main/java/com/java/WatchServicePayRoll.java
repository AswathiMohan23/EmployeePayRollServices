package com.java;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatchServicePayRoll {
    private final WatchService watcher;
    private final Map<WatchKey,Path> dirWatchers;
     WatchServicePayRoll(Path dir) throws IOException {
         this.watcher= FileSystems.getDefault().newWatchService();
         this.dirWatchers=new HashMap<WatchKey,Path>();
         scanAndRegisterDirectories(dir);
     }



    private void registerDirWatchers(Path dir)throws IOException{
         WatchKey key=dir.register(watcher,ENTRY_CREATE,ENTRY_DELETE,ENTRY_MODIFY);
         dirWatchers.put(key,dir);
     }

     //Register the given directory and all its sub-directories, with the watch service
    private void scanAndRegisterDirectories(final Path start)throws IOException {
        //register directory and subdirectories
         Files.walkFileTree(start, new SimpleFileVisitor<Path >(){
            @Override
             public FileVisitResult preVisitDirectory(Path dir,BasicFileAttributes attrs)throws IOException{
                registerDirWatchers(dir);
                return FileVisitResult.CONTINUE;
            }

        });
    }

    // process all events for keys queued to the watcher

   @SuppressWarnings({"rawtypes","unchecked"})
    void processEvents(){
         while(true){
             WatchKey key; //wait for key to be signalled
             try{
                 key=watcher.take();
             }catch(InterruptedException x){
                 return;
             }
             Path dir=dirWatchers.get(key);
             if(dir==null)
                 continue;
             for(WatchEvent<?>event:key.pollEvents()){
                 WatchEvent.Kind kind=event.kind();
                 Path name=((WatchEvent<Path>)event).context();
                 Path child=dir.resolve(name);
                 //print out event
                 System.out.format("%s: %s\n",event.kind().name(),child);
                 // if directory is created, then register it and its sub-directories
                 if(kind == ENTRY_CREATE) {
                     try {
                         if (Files.isDirectory(child)) scanAndRegisterDirectories(child);
                     } catch (IOException e) {
                     }
                 }else if (kind.equals(ENTRY_DELETE)){
                     if (Files.isDirectory(child)) dirWatchers.remove(key);
                 }
             }
            // reset key and remove from set if directory no longer accessible
             boolean valid = key.reset();
             if(!valid){
                 dirWatchers.remove(key);
                 if(dirWatchers.isEmpty())
                     break;
                 }
             }
         }

    }

