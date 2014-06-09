package jm.music.tools.ga;

import jm.music.data.Phrase;

public abstract class Mutater extends GAComponent
{
  public abstract Phrase[] mutate(Phrase[] paramArrayOfPhrase, double paramDouble, int paramInt1, int paramInt2);
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.Mutater
 * JD-Core Version:    0.5.3
 */