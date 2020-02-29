
public class ReceptorNueron extends Nueron
{
	//var
	protected int bias;
	protected int[] weights;
	
	//constuc
	public ReceptorNueron(int l, double v, int b, int[] w)
	{
		super(l, v);
		bias = b;
		weights = w;
	}
	
	//getrs
	public int getBias()
	{
		return bias;
	}
	public int[] getWeight()
	{
		return weights;
	}
	
	//setrs
	public void setBias(int bias)
	{
		this.bias = bias;
	}
	public void setWeights(int[] weights)
	{
		this.weights = weights;
	}
	public void setValue(double value)
	{
		//calculateVallue
	}
}
