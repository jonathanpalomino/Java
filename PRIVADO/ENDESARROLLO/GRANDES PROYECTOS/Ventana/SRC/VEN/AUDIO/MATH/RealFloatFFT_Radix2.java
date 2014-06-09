/*     */ package ven.audio.math;
/*     */ 
public class RealFloatFFT_Radix2 extends RealFloatFFT
{
    private int logn;

    public RealFloatFFT_Radix2(int i) {
	super(i);
	logn = Factorize.log2(i);
	if (logn < 0)
	    throw new IllegalArgumentException(i + " is not a power of 2");
    }

    public void transform(float[] fs, int i, int i_0_) {
	checkData(fs, i, i_0_);
	if (n != 1) {
	    bitreverse(fs, i, i_0_);
	    int i_1_ = 1;
	    int i_2_ = n;
	    for (int i_3_ = 1; i_3_ <= logn; i_3_++) {
		int i_4_ = i_1_;
		i_1_ = 2 * i_1_;
		i_2_ /= 2;
		for (int i_5_ = 0; i_5_ < i_2_; i_5_++) {
		    float f = (fs[i + i_0_ * i_5_ * i_1_]
			       + fs[i + i_0_ * (i_5_ * i_1_ + i_4_)]);
		    float f_6_ = (fs[i + i_0_ * i_5_ * i_1_]
				  - fs[i + i_0_ * (i_5_ * i_1_ + i_4_)]);
		    fs[i + i_0_ * i_5_ * i_1_] = f;
		    fs[i + i_0_ * (i_5_ * i_1_ + i_4_)] = f_6_;
		}
		float f = 1.0F;
		float f_7_ = 0.0F;
		double d = -6.283185307179586 / (double) i_1_;
		float f_8_ = (float) Math.sin(d);
		float f_9_ = (float) Math.sin(d / 2.0);
		float f_10_ = 2.0F * f_9_ * f_9_;
		for (int i_11_ = 1; i_11_ < i_4_ / 2; i_11_++) {
		    float f_12_ = f - f_8_ * f_7_ - f_10_ * f;
		    float f_13_ = f_7_ + f_8_ * f - f_10_ * f_7_;
		    f = f_12_;
		    f_7_ = f_13_;
		    for (int i_14_ = 0; i_14_ < i_2_; i_14_++) {
			f_12_ = fs[i + i_0_ * (i_14_ * i_1_ + i_11_)];
			f_13_ = fs[i + i_0_ * (i_14_ * i_1_ + i_4_ - i_11_)];
			float f_15_
			    = fs[i + i_0_ * (i_14_ * i_1_ + i_4_ + i_11_)];
			float f_16_
			    = fs[i + i_0_ * (i_14_ * i_1_ + i_1_ - i_11_)];
			fs[i + i_0_ * (i_14_ * i_1_ + i_11_)]
			    = f_12_ + f * f_15_ - f_7_ * f_16_;
			fs[i + i_0_ * (i_14_ * i_1_ + i_1_ - i_11_)]
			    = f_13_ + f * f_16_ + f_7_ * f_15_;
			fs[i + i_0_ * (i_14_ * i_1_ + i_4_ - i_11_)]
			    = f_12_ - f * f_15_ + f_7_ * f_16_;
			fs[i + i_0_ * (i_14_ * i_1_ + i_4_ + i_11_)]
			    = -(f_13_ - f * f_16_ - f_7_ * f_15_);
		    }
		}
		if (i_4_ > 1) {
		    for (int i_17_ = 0; i_17_ < i_2_; i_17_++)
			fs[i + i_0_ * (i_17_ * i_1_ + i_1_ - i_4_ / 2)]
			    *= -1.0F;
		}
	    }
	}
    }

    public void backtransform(float[] fs, int i, int i_18_) {
	checkData(fs, i, i_18_);
	if (n != 1) {
	    int i_19_ = n;
	    int i_20_ = 1;
	    int i_21_ = n / 2;
	    for (int i_22_ = 1; i_22_ <= logn; i_22_++) {
		for (int i_23_ = 0; i_23_ < i_20_; i_23_++) {
		    float f = fs[i + i_18_ * i_23_ * i_19_];
		    float f_24_ = fs[i + i_18_ * (i_23_ * i_19_ + i_21_)];
		    fs[i + i_18_ * i_23_ * i_19_] = f + f_24_;
		    fs[i + i_18_ * (i_23_ * i_19_ + i_21_)] = f - f_24_;
		}
		float f = 1.0F;
		float f_25_ = 0.0F;
		double d = 6.283185307179586 / (double) i_19_;
		float f_26_ = (float) Math.sin(d);
		float f_27_ = (float) Math.sin(d / 2.0);
		float f_28_ = 2.0F * f_27_ * f_27_;
		for (int i_29_ = 1; i_29_ < i_21_ / 2; i_29_++) {
		    float f_30_ = f - f_26_ * f_25_ - f_28_ * f;
		    float f_31_ = f_25_ + f_26_ * f - f_28_ * f_25_;
		    f = f_30_;
		    f_25_ = f_31_;
		    for (int i_32_ = 0; i_32_ < i_20_; i_32_++) {
			float f_33_ = fs[i + i_18_ * (i_32_ * i_19_ + i_29_)];
			float f_34_
			    = fs[i + i_18_ * (i_32_ * i_19_ + i_19_ - i_29_)];
			float f_35_
			    = fs[i + i_18_ * (i_32_ * i_19_ + i_21_ - i_29_)];
			float f_36_
			    = -fs[i + i_18_ * (i_32_ * i_19_ + i_21_ + i_29_)];
			fs[i + i_18_ * (i_32_ * i_19_ + i_29_)]
			    = f_33_ + f_35_;
			fs[i + i_18_ * (i_32_ * i_19_ + i_21_ - i_29_)]
			    = f_34_ + f_36_;
			float f_37_ = f_33_ - f_35_;
			float f_38_ = f_34_ - f_36_;
			fs[i + i_18_ * (i_32_ * i_19_ + i_21_ + i_29_)]
			    = f * f_37_ - f_25_ * f_38_;
			fs[i + i_18_ * (i_32_ * i_19_ + i_19_ - i_29_)]
			    = f * f_38_ + f_25_ * f_37_;
		    }
		}
		if (i_21_ > 1) {
		    for (int i_39_ = 0; i_39_ < i_20_; i_39_++) {
			fs[i + i_18_ * (i_39_ * i_19_ + i_21_ / 2)] *= 2.0F;
			fs[i + i_18_ * (i_39_ * i_19_ + i_21_ + i_21_ / 2)]
			    *= -2.0F;
		    }
		}
		i_21_ /= 2;
		i_19_ /= 2;
		i_20_ *= 2;
	    }
	    bitreverse(fs, i, i_18_);
	}
    }

    public float[] toWraparoundOrder(float[] fs) {
	return toWraparoundOrder(fs, 0, 1);
    }

    public float[] toWraparoundOrder(float[] fs, int i, int i_40_) {
	checkData(fs, i, i_40_);
	float[] fs_41_ = new float[2 * n];
	int i_42_ = n / 2;
	fs_41_[0] = fs[i];
	fs_41_[1] = 0.0F;
	fs_41_[n] = fs[i + i_40_ * i_42_];
	fs_41_[n + 1] = 0.0F;
	for (int i_43_ = 1; i_43_ < i_42_; i_43_++) {
	    fs_41_[2 * i_43_] = fs[i + i_40_ * i_43_];
	    fs_41_[2 * i_43_ + 1] = fs[i + i_40_ * (n - i_43_)];
	    fs_41_[2 * (n - i_43_)] = fs[i + i_40_ * i_43_];
	    fs_41_[2 * (n - i_43_) + 1] = -fs[i + i_40_ * (n - i_43_)];
	}
	return fs_41_;
    }

    protected void bitreverse(float[] fs, int i, int i_44_) {
	int i_45_ = 0;
	int i_46_ = 0;
	for (/**/; i_45_ < n - 1; i_45_++) {
	    int i_47_ = n / 2;
	    if (i_45_ < i_46_) {
		float f = fs[i + i_44_ * i_45_];
		fs[i + i_44_ * i_45_] = fs[i + i_44_ * i_46_];
		fs[i + i_44_ * i_46_] = f;
	    }
	    for (/**/; i_47_ <= i_46_; i_47_ /= 2)
		i_46_ -= i_47_;
	    i_46_ += i_47_;
	}
    }
}
