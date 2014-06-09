package jm.midi.event;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract interface Event
{
  public abstract int getTime();

  public abstract void setTime(int paramInt);

  public abstract short getID();

  public abstract Event copy()
    throws CloneNotSupportedException;

  public abstract void print();

  public abstract int write(DataOutputStream paramDataOutputStream)
    throws IOException;

  public abstract int read(DataInputStream paramDataInputStream)
    throws IOException;
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.Event
 * JD-Core Version:    0.5.3
 */