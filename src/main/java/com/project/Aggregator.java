package com.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Aggregator extends AbstractActor {

    public static final String START_OF_FILE = "start-of-file";
    public static final String LINE = "line";
    public static final String END_OF_FILE = "end-of-file";

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public String content;
    public String pathName;
    public ArrayList<String> words = new ArrayList<String>();
    public Integer wordCount;

    public Aggregator(String pathName, String content) {
        this.pathName = pathName;
        this.content = content;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals(START_OF_FILE, m -> {
            this.handleStartOfFile();
        }).matchEquals(LINE, m -> {
            this.handleLine();
        }).matchEquals(END_OF_FILE, m -> {
            this.handleEndOfFile();
        }).matchAny(obj -> {
            log.info("unknown");
        }).build();
    }

    private void handleStartOfFile() {
        for (String word : this.content.split(" ")) {
            this.words.add(word);
        }
    }

    private void handleLine() {
        this.wordCount = this.words.size();
    }

    private void handleEndOfFile() {
        System.out.println(this.pathName + " gets word count: " + this.wordCount);
    }
}
