package com.univer.algorithm;

import java.math.BigInteger;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class PollardDiscr {

  private long prime;
  private long alpha;
  private long beta;

  public PollardDiscr(long alpha, long beta, long prime) {
    this.prime = prime;
    this.alpha = alpha;
    this.beta = beta;
  }

  public Long mod(Long a, Long b) {
    return Math.floorMod(a, b);
  }

  public Long getX() {
    Long n = (long) this.Euler(Integer.valueOf((int) this.prime));

    Long a1 = 0L;
    Long a2 = 0L;

    Long b1 = 0L;
    Long b2 = 0L;

    Long x1 = 1L;
    Long x2 = 1L;

    if (this.alpha == this.beta) {
      return 1L;
    }

    Boolean isStart = true;
    while (!x1.equals(x2) || isStart) {
      isStart = false;

      if (x1 < this.prime / 3) {
        x1 = this.mod((this.beta * x1), this.prime);
        a1 = a1;
        b1 = this.mod((b1 + 1), n);
      } else {
        if (x1 >= (this.prime / 3) && x1 < (2 * this.prime / 3)) {
          x1 = this.mod((x1 * x1), this.prime);
          a1 = this.mod((2 * a1), n);
          b1 = this.mod((2 * b1), n);
        } else {
          if (x1 >= (2 * this.prime / 3)) {
            x1 = this.mod((this.alpha * x1), this.prime);
            a1 = this.mod((a1 + 1), n);
            b1 = b1;
          }
        }
      }

      for (int i = 0; i < 2; ++i) {
        if (x2 < this.prime / 3) {
          x2 = this.mod((this.beta * x2), this.prime);
          a2 = a2;
          b2 = this.mod((b2 + 1), n);
        } else {
          if (x2 >= (this.prime / 3) && x2 < (2 * this.prime / 3)) {
            x2 = this.mod((x2 * x2), this.prime);
            a2 = this.mod((2 * a2), n);
            b2 = this.mod((2 * b2), n);
          } else {
            if (x2 >= (2 * this.prime / 3)) {
              x2 = this.mod((this.alpha * x2), this.prime);
              a2 = this.mod((a2 + 1), n);
              b2 = b2;
            }
          }
        }

      }
    }

    Long u = this.mod((a1 - a2), n);
    Long v = this.mod((b2 - b1), n);

    if (this.mod(v, n) == 0) {
      System.err.println("Not found x!");
      return 0L;
    }

//    final Euclid euclid = new Euclid().countEuclid(BigInteger.valueOf(v), BigInteger.valueOf(n));
//    Long d = Long.valueOf(euclid.getNOD());
    final long d = BigInteger.valueOf(v).gcd(BigInteger.valueOf(n)).longValue();
    final Euclid euclid = new Euclid().countEuclid(BigInteger.valueOf(n), BigInteger.valueOf(v));
    final long l = Math.abs(Long.valueOf(euclid.getX()) * d);
    for (int i = 0; i <= d; ++i) {
      final long l1 = l + i * l;

      final BigInteger pow = BigInteger.valueOf(this.alpha)
          .modPow(BigInteger.valueOf(l1), BigInteger.valueOf(this.prime));
      final long l2 = pow.intValue() - this.beta;
      final Long mod = this.mod((long) (Math.pow(this.alpha, l1) - this.beta), this.prime);
      if (l2 == 0) {
        return l1;
      }
    }
    for (int i = 0; i <= d; ++i) {
      final long l1 = l + i * (n/d);

      final BigInteger pow = BigInteger.valueOf(this.alpha)
          .modPow(BigInteger.valueOf(l1), BigInteger.valueOf(this.prime));
      final long l2 = pow.intValue() - this.beta;
      final Long mod = this.mod((long) (Math.pow(this.alpha, l1) - this.beta), this.prime);
      if (l2 == 0) {
        return l1;
      }

    }
    double nu = Math.pow(v, -1) % n;
    nu = Math.pow(v, -1);
    if (d == 1) {
      nu = this.getOposite(v.intValue(), n.intValue());
    }
    double x = 0L;
    //long m = this.mod(v, d) == 0 ? d : this.mod(v, d);
    final double v1 = u /v;
    for (int i = 0; i != d + 1; ++i) {
      x = this.mod((long) ((v1 + i * n) / d), (long) n);
      double mod = this.mod((long) (Math.pow(this.alpha, x) - this.beta), this.prime);
      if (mod == 0) {
        return (long) x;
      }
    }
    //System.err.println("Not found x!");
    return (long) x;
  }

  public Long premutiveX() {
    Long x = 0L;
    while (x != this.prime) {
      final double mod = (Math.pow(this.alpha, x) - this.beta) % this.prime;
      if (mod == 0) {
        return x;
      }
      x++;
    }
    return 0L;
  }

  public Integer Euler(Integer n) {
    Integer result = 1;
    for (int i = 2; i * i <= n; ++i) {
      Integer p = 1;
      while (n % i == 0) {
        n /= i;
        p *= i;
      }
      p /= i;
      if (p != 0) {
        result *= (p * (i - 1));
      }
    }
    Integer m = n - 1;
    if (m == 0) {
      return result;
    } else {
      return m * result;
    }

  }

  public Integer getOposite(Integer v, Integer n) {
    return BigInteger.valueOf(v).modInverse(BigInteger.valueOf(n)).intValue();
  }

  //  private Long nextU(Long u, Long z) {
//    Long newU = 0L;
//    Long xPrime = this.prime - 1;
//    if (z < this.prime / 3) {
//      newU = (u + 1) % xPrime;
//    }
//    if (z > (this.prime / 3) && z < (2 * this.prime / 3)) {
//      newU = (2 * u) % xPrime;
//    }
//    if (z > (2 * this.prime / 3)) {
//      newU = u % xPrime;
//    }
//    return newU;
//  }
//
//  private Long nextV(Long v, Long z) {
//    Long newV = 0L;
//    Long xPrime = this.prime - 1;
//    if (z < this.prime / 3) {
//      newV = v % xPrime;
//    }
//    if (z > (this.prime / 3) && z < (2 * this.prime / 3)) {
//      newV = (2 * v) % xPrime;
//    }
//    if (z > (2 * this.prime / 3)) {
//      newV = (v + 1) % xPrime;
//    }
//    return newV;
//  }
//
//  private Long nextZ(Long z) {
//    Long newZ = 0L;
//
//  }

}
