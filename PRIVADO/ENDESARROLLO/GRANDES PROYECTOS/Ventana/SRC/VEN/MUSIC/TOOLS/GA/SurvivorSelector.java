package jm.music.tools.ga;

import jm.music.data.Phrase;

public abstract class SurvivorSelector extends GAComponent
{
  public abstract Phrase[] selectSurvivors(Phrase[] paramArrayOfPhrase1, double[] paramArrayOfDouble1, Phrase[] paramArrayOfPhrase2, double[] paramArrayOfDouble2);
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.SurvivorSelector
 * JD-Core Version:    0.5.3
 */