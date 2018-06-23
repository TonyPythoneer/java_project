import akka.actor.AbstractActor;

public class FileParser extends AbstractActor {

    public static final String START_OF_FILE = "start-of-file";
    public static final String LINE = "line";
    public static final String END_OF_FILE = "end-of-file";

    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals(START_OF_FILE, m -> {
            System.out.println(START_OF_FILE);
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