import com.project.FileScanner;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        ActorSystem mySystem = ActorSystem.create("mySystem");
        ActorRef fileScannerActor = mySystem.actorOf(Props.create(FileScanner.class, "./files"), "fileScanner");

        fileScannerActor.tell(FileScanner.SCAN, ActorRef.noSender());
    }
}
