/*     */ package jm.music.tools.ga;
/*     */ 
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class PhrGeneticAlgorithm
/*     */ {
/*     */   protected Phrase[] population;
/*     */   protected double[] fitness;
/*     */   protected PopulationInitialiser populationInitialiser;
/*     */   protected FitnessEvaluater fitnessEvaluater;
/*     */   protected TerminationCriteria terminationCriteria;
/*     */   protected ParentSelector parentSelector;
/*     */   protected Recombiner recombiner;
/*     */   protected Mutater mutater;
/*     */   protected SurvivorSelector survivorSelector;
/*     */   protected int beatsPerBar;
/*     */   protected long iteration;
/*     */   protected double initialLength;
/*     */   protected int initialSize;
/*     */   protected int originalSize;
/*     */   protected boolean finished;
/*     */ 
/*     */   public PhrGeneticAlgorithm(Phrase paramPhrase, int paramInt, PopulationInitialiser paramPopulationInitialiser, FitnessEvaluater paramFitnessEvaluater, TerminationCriteria paramTerminationCriteria, ParentSelector paramParentSelector, Recombiner paramRecombiner, Mutater paramMutater, SurvivorSelector paramSurvivorSelector)
/*     */   {
/*  71 */     this.beatsPerBar = paramInt;
/*  72 */     this.initialLength = paramPhrase.getEndTime();
/*  73 */     this.initialSize = paramPhrase.size();
/*  74 */     this.originalSize = this.initialSize;
/*     */ 
/*  76 */     this.populationInitialiser = paramPopulationInitialiser;
/*  77 */     this.fitnessEvaluater = paramFitnessEvaluater;
/*  78 */     this.terminationCriteria = paramTerminationCriteria;
/*  79 */     this.parentSelector = paramParentSelector;
/*  80 */     this.recombiner = paramRecombiner;
/*  81 */     this.mutater = paramMutater;
/*  82 */     this.survivorSelector = paramSurvivorSelector;
/*     */ 
/*  84 */     setUpNewPopulation(paramPhrase);
/*     */   }
/*     */ 
/*     */   public void setUpNewPopulation(Phrase paramPhrase)
/*     */   {
/*  89 */     this.iteration = 0L;
/*  90 */     this.population = this.populationInitialiser.initPopulation(paramPhrase, this.beatsPerBar);
/*  91 */     this.fitness = this.fitnessEvaluater.evaluate(this.population);
/*     */   }
/*     */ 
/*     */   public void setBeatsPerBar(int paramInt) {
/*  95 */     this.beatsPerBar = paramInt;
/*     */   }
/*     */ 
/*     */   public void zeroInitialSize() {
/*  99 */     this.originalSize = this.initialSize;
/* 100 */     this.initialSize = 0;
/*     */   }
/*     */ 
/*     */   public void restoreInitialSize() {
/* 104 */     this.initialSize = this.originalSize;
/*     */   }
/*     */ 
/*     */   public long getIteration() {
/* 108 */     return this.iteration;
/*     */   }
/*     */ 
/*     */   public boolean iterate()
/*     */   {
/* 115 */     this.finished = this.terminationCriteria.isFinished(this.population);
/* 116 */     if (!(this.finished)) {
/* 117 */       this.iteration += 1L;
/*     */ 
/* 119 */       Phrase[] arrayOfPhrase1 = this.parentSelector.selectParents(this.population, this.fitness);
/* 120 */       Phrase[] arrayOfPhrase2 = this.recombiner.recombine(arrayOfPhrase1, this.fitness, this.initialLength, this.initialSize, this.beatsPerBar);
/*     */ 
/* 122 */       Phrase[] arrayOfPhrase3 = this.mutater.mutate(arrayOfPhrase2, this.initialLength, this.initialSize, this.beatsPerBar);
/*     */ 
/* 124 */       double[] arrayOfDouble = this.fitnessEvaluater.evaluate(arrayOfPhrase3);
/* 125 */       this.population = this.survivorSelector.selectSurvivors(this.population, this.fitness, arrayOfPhrase3, arrayOfDouble);
/*     */ 
/* 128 */       this.fitness = this.fitnessEvaluater.evaluate(this.population);
/*     */ 
/* 130 */       return true;
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   public long iterate(long paramLong)
/*     */   {
/* 141 */     long l = paramLong;
/* 142 */     for (int i = 0; i < paramLong; ++i) {
/* 143 */       iterate();
/* 144 */       if (this.finished) {
/* 145 */         l = i;
/* 146 */         break;
/*     */       }
/*     */     }
/* 149 */     return l;
/*     */   }
/*     */ 
/*     */   public double[] getFitness() {
/* 153 */     return this.fitness;
/*     */   }
/*     */ 
/*     */   public Phrase[] getPopulation() {
/* 157 */     return this.population;
/*     */   }
/*     */ 
/*     */   public Phrase[] getOrderedPopulation()
/*     */   {
/* 164 */     quicksort();
/* 165 */     return this.population;
/*     */   }
/*     */ 
/*     */   private void quicksort() {
/* 169 */     quicksort(0, this.population.length - 1);
/*     */   }
/*     */ 
/*     */   private void quicksort(int paramInt1, int paramInt2)
/*     */   {
/* 175 */     if (paramInt1 >= paramInt2) {
/* 176 */       return;
/*     */     }
/* 178 */     swap(paramInt1, rand(paramInt1, paramInt2));
/* 179 */     int i = paramInt1;
/* 180 */     for (int j = paramInt1 + 1; j <= paramInt2; ++j) {
/* 181 */       if (this.fitness[j] < this.fitness[paramInt1]) {
/* 182 */         swap(++i, j);
/*     */       }
/*     */     }
/* 185 */     swap(paramInt1, i);
/* 186 */     quicksort(paramInt1, i - 1);
/* 187 */     quicksort(i + 1, paramInt2);
/*     */   }
/*     */ 
/*     */   private static int rand(int paramInt1, int paramInt2) {
/* 191 */     return (paramInt1 + (int)(Math.random() * (paramInt2 - paramInt1)) + 1);
/*     */   }
/*     */ 
/*     */   private void swap(int paramInt1, int paramInt2) {
/* 195 */     Phrase localPhrase = this.population[paramInt1];
/* 196 */     this.population[paramInt1] = this.population[paramInt2];
/* 197 */     this.population[paramInt2] = localPhrase;
/*     */ 
/* 199 */     double d = this.fitness[paramInt1];
/* 200 */     this.fitness[paramInt1] = this.fitness[paramInt2];
/* 201 */     this.fitness[paramInt2] = d;
/*     */   }
/*     */ 
/*     */   public double getBestFitness() {
/* 205 */     double d = 0.0D;
/* 206 */     int i = -1;
/* 207 */     for (int j = 0; j < this.fitness.length; ++j) {
/* 208 */       if (this.fitness[j] > d) {
/* 209 */         d = this.fitness[j];
/* 210 */         i = j;
/*     */       }
/*     */     }
/* 213 */     return d;
/*     */   }
/*     */ 
/*     */   public double getAverageFitness() {
/* 217 */     double d = 0.0D;
/* 218 */     for (int i = 0; i < this.fitness.length; ++i) {
/* 219 */       d += this.fitness[i];
/*     */     }
/* 221 */     return (d / this.fitness.length);
/*     */   }
/*     */ 
/*     */   public double getStandardDeviation() {
/* 225 */     double d1 = getAverageFitness();
/* 226 */     double d2 = 0.0D;
/* 227 */     for (int i = 0; i < this.fitness.length; ++i) {
/* 228 */       d2 += (d1 - this.fitness[i]) * (d1 - this.fitness[i]);
/*     */     }
/* 230 */     return (Math.sqrt(d2) / this.fitness.length);
/*     */   }
/*     */ 
/*     */   public Phrase getBestIndividual() {
/* 234 */     double d = 1.7976931348623157E+308D;
/* 235 */     int i = -1;
/* 236 */     for (int j = 0; j < this.fitness.length; ++j) {
/* 237 */       if (this.fitness[j] < d) {
/* 238 */         d = this.fitness[j];
/* 239 */         i = j;
/*     */       }
/*     */     }
/* 242 */     return this.population[i];
/*     */   }
/*     */ 
/*     */   public Mutater getMutater() {
/* 246 */     return this.mutater;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.PhrGeneticAlgorithm
 * JD-Core Version:    0.5.3
 */