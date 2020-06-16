import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.mockito.Mockito.*;

class BallsPhysicsTest {
    private RemoteEndpoint.Basic mockBasic(ArgumentMatcher<String> match) {
        try {
            RemoteEndpoint.Basic basic = mock(RemoteEndpoint.Basic.class);
            doNothing().when(basic).sendObject(Mockito.argThat(match));
            return basic;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Session mockSession(String id, ArgumentMatcher<String> match, RemoteEndpoint.Basic endPoint) {
        Session s = mock(Session.class);
        when(s.getId()).thenReturn(id);
        when(s.isOpen()).thenReturn(true);
        RemoteEndpoint.Basic mockBasic = mockBasic(match);
        when(s.getBasicRemote()).thenReturn(mockBasic);
        return s;
    }

    private final ArgumentMatcher<String> matcher = argument -> true;

    private final RemoteEndpoint.Basic mockedEndPoint = mockBasic(matcher);
    private final Session mockedSession = mockSession("session", matcher, mockedEndPoint);

    @Test
    void iterate() {
        AtomicBoolean isAlive = new AtomicBoolean(true);
        BallsPhysics physics = new BallsPhysics(mockedSession, isAlive, 100);
        for (int i = 0; i < 30; i++)
            physics.iterate();

        verify(mockedSession, times(30)).getBasicRemote();
    }
}