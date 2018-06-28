package com.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class FileScanner extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public final static String SCAN = "scan";

    public Path directory;

    public FileScanner(String directoryName) {
        this.directory = Paths.get(directoryName).toAbsolutePath();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals(SCAN, str -> {
            this.handle();
        }).matchAny(obj -> {
            log.info("FileScanner receive unknown");
        }).build();
    }

    private void handle() throws IOException {
        Files.list(this.directory).filter(Files::isRegularFile).forEach(fileNamePath -> {
            log.info("Create fileParser");
            String actorName = "file-parser-" + fileNamePath.toAbsolutePath().hashCode();
            ActorRef fileParser = getContext().actorOf(Props.create(FileParser.class, fileNamePath), actorName);
            fileParser.tell(FileParser.PARSE, ActorRef.noSender());
        });
    }
}
