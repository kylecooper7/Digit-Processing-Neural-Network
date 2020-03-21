package program;

public class ReceptorNueron extends Nueron
{
	//var
	protected int bias;
	protected int[] weights;
	protected BackpropInfo bpi;
	
	//constuc
	public ReceptorNueron(int l, double v, int b, int[] w)
	{
		super(l, v);
		bias = b;
		weights = w;
		bpi = new BackpropInfo();
	}
	
	//getrs
	public int getBias()
	{
		return bias;
	}
	public int[] getWeights()
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
	public void setValue(double valoo)
	{
		//calculateVallue
		this.value = valoo;
	}

	public BackpropInfo getBpi() {
		return bpi;
	}

	public void setBpi(BackpropInfo bpi) {
		this.bpi = bpi;
	}
}
