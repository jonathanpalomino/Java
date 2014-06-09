package jm.midi;

import jm.midi.event.Event;

public abstract interface MidiInputListener
{
  public abstract void newEvent(Event paramEvent);
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.MidiInputListener
 * JD-Core Version:    0.5.3
 */