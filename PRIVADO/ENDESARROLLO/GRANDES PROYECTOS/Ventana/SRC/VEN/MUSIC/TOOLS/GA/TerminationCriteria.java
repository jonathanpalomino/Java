package jm.music.tools.ga;

import jm.music.data.Phrase;

public abstract class TerminationCriteria extends GAComponent
{
  public abstract boolean isFinished(Phrase[] paramArrayOfPhrase);
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.TerminationCriteria
 * JD-Core Version:    0.5.3
 */