/*    */ package ven.audio.math;
/*    */ 
/*    */public class Factorize
{
    public static int[] factor(int i, int[] is) {
	int[] is_0_ = new int[64];
	int i_1_ = 0;
	int i_2_ = i;
	if (i <= 0)
	    throw new Error("Number (" + i + ") must be positive integer");
	for (int i_3_ = 0; i_3_ < is.length && i_2_ != 1; i_3_++) {
	    int i_4_ = is[i_3_];
	    while (i_2_ % i_4_ == 0) {
		i_2_ /= i_4_;
		is_0_[i_1_++] = i_4_;
	    }
	}
	int i_5_ = 2;
	while (i_2_ % i_5_ == 0 && i_2_ != 1) {
	    i_2_ /= i_5_;
	    is_0_[i_1_++] = i_5_;
	}
	i_5_ = 3;
	while (i_2_ != 1) {
	    for (/**/; i_2_ % i_5_ != 0; i_5_ += 2) {
		/* empty */
	    }
	    i_2_ /= i_5_;
	    is_0_[i_1_++] = i_5_;
	}
	int i_6_ = 1;
	for (int i_7_ = 0; i_7_ < i_1_; i_7_++)
	    i_6_ *= is_0_[i_7_];
	if (i_6_ != i)
	    throw new Error("factorization failed for " + i);
	int[] is_8_ = new int[i_1_];
	System.arraycopy(is_0_, 0, is_8_, 0, i_1_);
	return is_8_;
    }

    public static int log2(int i) {
	int i_9_ = 0;
	int i_10_ = 1;
	while (i_10_ < i) {
	    i_10_ *= 2;
	    i_9_++;
	}
	if (i != 1 << i_9_)
	    return -1;
	return i_9_;
    }
}
