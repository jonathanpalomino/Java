package jm.util;

import jm.music.data.Score;

public abstract interface ReadListener
{
  public abstract Score scoreRead(Score paramScore);

  public abstract void startedReading();

  public abstract void finishedReading();
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.ReadListener
 * JD-Core Version:    0.5.3
 */