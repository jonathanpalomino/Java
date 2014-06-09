package jm.midi.event;

public abstract interface VoiceEvt extends Event
{
  public abstract short getMidiChannel();

  public abstract void setMidiChannel(short paramShort);
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.VoiceEvt
 * JD-Core Version:    0.5.3
 */