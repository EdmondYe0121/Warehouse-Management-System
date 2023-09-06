package event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// refer from alarm system
/**
 * Unit tests for the EventLog class
 */
public class EventLogTest {
	private Event event1;
	private Event event2;
	private Event event3;
	
	@BeforeEach
	public void loadEvents() {
		event1 = new Event("A1");
		event2 = new Event("A2");
		event3 = new Event("A3");
		EventLog el = EventLog.getInstance();
		el.logEvent(event1);
		el.logEvent(event2);
		el.logEvent(event3);
	}
	
	@Test
	public void testLogEvent() {	
		List<Event> l = new ArrayList<Event>();
		
		EventLog el = EventLog.getInstance();
		for (Event next : el) {
			l.add(next);
		}
		
		assertTrue(l.contains(event1));
		assertTrue(l.contains(event2));
		assertTrue(l.contains(event3));
	}

	@Test
	public void testClear() {
		EventLog el = EventLog.getInstance();
		el.clear();
		Iterator<Event> itr = el.iterator();
		assertTrue(itr.hasNext());   // After log is cleared, the clear log event is added
		assertEquals("Event log cleared.", itr.next().getDescription());
		assertFalse(itr.hasNext());
	}
}
