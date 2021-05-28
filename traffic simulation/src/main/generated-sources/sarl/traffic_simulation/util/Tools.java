package traffic_simulation.util;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author jeome
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Tools {
  public Tools() {
  }
  
  @Pure
  public double f(final double x1, final double xc, final double x2, final double t) {
    double _power = Math.pow((1.0d - t), 2.0d);
    double _power_1 = Math.pow(t, 2.0d);
    return (((x1 * _power) + (((2.0d * xc) * t) * (1.0d - t))) + (x2 * _power_1));
  }
  
  /**
   * def g(P : double[], t : double){
   * return Math.sqrt(f(P.get(0),P.get(1),P.get(2),t)**2
   * }
   */
  @Pure
  public double deriv_bez(final double x1, final double xc, final double x2, final double t) {
    return (((((-2.0d) * x1) * (1.0d - t)) + ((2.0d * xc) * (1.0d - (2.0d * t)))) + ((2.0d * x2) * t));
  }
  
  @Pure
  public double g(final double[] P, final double t) {
    double _deriv_bez = this.deriv_bez(P[0], P[1], P[2], t);
    double _power = Math.pow(_deriv_bez, 2);
    double _deriv_bez_1 = this.deriv_bez(P[3], P[4], P[5], t);
    double _power_1 = Math.pow(_deriv_bez_1, 2);
    return Math.sqrt(
      (_power + _power_1));
  }
  
  public double weight_curve(final double[] P, final double a, final double b, final double n) {
    double m = a;
    double d = (b - a);
    double h = (d / n);
    double _g = this.g(P, a);
    double _divide = (_g / 2);
    double _g_1 = this.g(P, b);
    double I = (h * (_divide + (_g_1 / 2)));
    for (int i = 0; (i < n); i++) {
      {
        double _g_2 = this.g(P, (m + h));
        I = (I + (h * _g_2));
        m = (m + h);
      }
    }
    return I;
  }
  
  @Pure
  public double accelerationFree(final double a, final double Va, final double V0) {
    double res = 0;
    double _power = Math.pow((Va / V0), 4);
    res = (a * (1 - _power));
    return res;
  }
  
  @Pure
  public double accelerationInt(final double a, final double Va, final double b, final double Sa, final double S0, final double delta, final double T) {
    double res = 0;
    double _sqrt = Math.sqrt((a * b));
    double _power = Math.pow((((S0 + (Va * T)) / Sa) + ((Va * delta) / ((2 * _sqrt) * Sa))), 2);
    res = ((-a) * _power);
    return res;
  }
  
  @Pure
  public double calc_position(final double v, final double t) {
    double res = 0;
    res = (v * t);
    return res;
  }
  
  @Pure
  public double calc_speed(final double acc, final double t) {
    double res = 0;
    res = (acc * t);
    return res;
  }
}
