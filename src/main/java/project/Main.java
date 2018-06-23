package project.main;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import akka.japi.pf.ReceiveBuilder;
import akka.actor.UntypedAbstractActor;

import FileParser;

/*
class MyActor extends UntypedAbstractActor {

    @Override
    public void onReceive(Object msg) throws Exception {
        System.out.println(msg);
    }
}

class FileParser extends UntypedAbstractActor {
    @Override
    public void onReceive(Object msg) throws Exception {
        System.out.println(msg);
    }
}

public class AAAMyActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(String.class, s -> {
            log.info("Received String message: {}", s);
        }).matchAny(o -> log.info("received unknown message")).build();

        receiveBuilder();
    }
}

class Main2 {
    public static void main(String[] args) {
        ActorSystem fileScanner = ActorSystem.create("fileScanner");
        ActorRef fileParser = fileScanner.actorOf(Props.create(FileParser.class), "fileParser");

        fileParser.tell("hello!", ActorRef.noSender());
    }
}
*/

public class Main {
    public static void main(String[] args) {
        ActorSystem mySystem = ActorSystem.create("mySystem");
        ActorRef myActor = mySystem.actorOf(Props.create(FileParser.class), "fileParser");

        myActor.tell(FileParser.START_OF_FILE, ActorRef.noSender());
    }
}
