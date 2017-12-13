package ch.psi.pshell.plot;

/**
 * Generator function that generates a gauss distribution
 * @author ebner
 *
 */
public class Gauss2D implements XYZGeneratorFunction{
	
	private double centerX;
	private double centerY;
	private double sigmaX;
	private double sigmaY;
	private double amplitude;
	
	public Gauss2D(double centerX, double centerY, double sigmaX, double sigmaY, double amplitude){
		this.centerX = centerX;
		this.centerY = centerY;
		this.sigmaX = sigmaX;
		this.sigmaY = sigmaY;
		this.amplitude = amplitude;
	}
	
	
	@Override
	public double generate(double x, double y) {
		return amplitude*Math.exp(-Math.pow(x - centerX, 2.0)/sigmaX)* Math.exp(-Math.pow(y - centerY, 2.0)/sigmaY);
	}
	
}
