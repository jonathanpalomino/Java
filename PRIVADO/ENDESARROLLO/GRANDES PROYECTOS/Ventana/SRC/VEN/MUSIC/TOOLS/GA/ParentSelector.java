package jm.music.tools.ga;

import jm.music.data.Phrase;

public abstract class ParentSelector extends GAComponent
{
  public abstract Phrase[] selectParents(Phrase[] paramArrayOfPhrase, double[] paramArrayOfDouble);
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.ParentSelector
 * JD-Core Version:    0.5.3
 */