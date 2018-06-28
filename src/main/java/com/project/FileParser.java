package com.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class FileParser extends AbstractActor {

    public static final String PARSE = "parse";

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Path fileNamePath;

    public FileParser(Path fileNamePath) {
        this.fileNamePath = fileNamePath;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals(PARSE, str -> {
            this.handle();
        }).matchAny(obj -> {
            log.info("FileParser receive unknown");
        }).build();
    }

    private void handle() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(this.fileNamePath.toString()))) {
            String[] lines = stream.toArray(String[]::new);
            String content = String.join(" ", lines);
            ActorRef aggregator = getContext().actorOf(
                    Props.create(Aggregator.class, this.fileNamePath.toString(), content),
                    "aggregator-" + this.fileNamePath.hashCode());
            aggregator.tell(Aggregator.START_OF_FILE, ActorRef.noSender());
            aggregator.tell(Aggregator.LINE, ActorRef.noSender());
            aggregator.tell(Aggregator.END_OF_FILE, ActorRef.noSender());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
