import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

@ServerEndpoint("/socket")
public class WebSocketEndpoint {
    private Thread coordinatesUpdater = null;
    private AtomicBoolean isAlive = new AtomicBoolean(false);

    @OnMessage(maxMessageSize = 1024000)
    public byte[] handleBinaryMessage(byte[] buffer) {
        System.out.println("New Binary Message Received");
        return buffer;
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("Received message " + msg);
        if (msg.equals("Start")) {
            System.out.println("START");
            for (Session sess : session.getOpenSessions()) {
                start(sess);
            }
        } else if (msg.equals("Stop")) {
            System.out.println("STOP");
            stop();
        } else if (msg.equals("INIT")) {
            for (Session sess : session.getOpenSessions()) {
                try {
                    sess.getBasicRemote().sendText(
                            "Z " + Const.A_X_POS + " " + Const.A_Y_POS + " " + Const.A_RADIUS +
                                    " " + Const.B_X_POS + " " + Const.B_Y_POS + " " + Const.B_RADIUS
                    );
                    System.out.println("Send initial values");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void start(Session session) {
        if (coordinatesUpdater == null) {
            coordinatesUpdater = new Thread(new BallsPhysics(session, isAlive, Const.SLEEP_TIME));
        } else if (coordinatesUpdater.isAlive()) {
            System.out.println("Can't start the service. Restarting...");
            isAlive.set(false);
        }

        if (!coordinatesUpdater.isAlive()) {
            System.out.println("Starting the service...");
            isAlive.set(true);
            coordinatesUpdater = new Thread(new BallsPhysics(session, isAlive, Const.SLEEP_TIME));
            coordinatesUpdater.start();
        }
    }

    private void stop() {
        if (coordinatesUpdater == null) {
            System.out.println("Start service before stopping it!");
        } else if (!coordinatesUpdater.isAlive()) {
            System.out.println("Service is dead. Consider starting it first...");
        } else if (coordinatesUpdater.isAlive()) {
            System.out.println("Stopping the service...");
            isAlive.set(false);
        }
    }
}
