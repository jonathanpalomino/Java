package jm.music.tools.ga;

import jm.music.data.Phrase;

public abstract class FitnessEvaluater extends GAComponent
{
  public abstract double[] evaluate(Phrase[] paramArrayOfPhrase);
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.FitnessEvaluater
 * JD-Core Version:    0.5.3
 */