import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.actor.AbstractActor;

import java.util.Scanner;

class FileParser extends AbstractActor {

    public static final String START_OF_FILE = "start-of-file";
    public static final String LINE = "line";
    public static final String END_OF_FILE = "end-of-file";

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals(START_OF_FILE, m -> {
            System.out.println(START_OF_FILE);
            log.info("Received String message: {}", m);
            // sender().tell(Msg.DONE, self());
        }).matchEquals(LINE, m -> {
            System.out.println(LINE);
            // sender().tell(Msg.DONE, self());
        }).matchEquals(END_OF_FILE, m -> {
            System.out.println(END_OF_FILE);
            // sender().tell(Msg.DONE, self());
        }).build();
    }
}

/*
 * class MyActor extends UntypedAbstractActor {
 *
 * @Override public void onReceive(Object msg) throws Exception {
 * System.out.println(msg); } }
 *
 * class FileParser extends UntypedAbstractActor {
 *
 * @Override public void onReceive(Object msg) throws Exception {
 * System.out.println(msg); } }
 *
 * public class AAAMyActor extends AbstractActor { private final LoggingAdapter
 * log = Logging.getLogger(getContext().getSystem(), this);
 *
 * @Override public Receive createReceive() { return
 * receiveBuilder().match(String.class, s -> {
 * log.info("Received String message: {}", s); }).matchAny(o ->
 * log.info("received unknown message")).build();
 *
 * receiveBuilder(); } }
 *
 * class Main2 { public static void main(String[] args) { ActorSystem
 * fileScanner = ActorSystem.create("fileScanner"); ActorRef fileParser =
 * fileScanner.actorOf(Props.create(FileParser.class), "fileParser");
 *
 * fileParser.tell("hello!", ActorRef.noSender()); } }
 */

public class Main {
    public static void main(String[] args) {
        ActorSystem mySystem = ActorSystem.create("mySystem");
        ActorRef myActor = mySystem.actorOf(Props.create(FileParser.class), "fileParser");

        Scanner scanner = new Scanner(System.in);

        myActor.tell(FileParser.START_OF_FILE, ActorRef.noSender());
    }
}
