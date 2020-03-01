package program;

public abstract class Nueron
{
	// var
	protected int layer;
	protected double value;

	// constuc
	public Nueron(int l, double v)
	{
		layer = l;
		value = v;
	}

	// getrs
	public int getLayer()
	{
		return layer;
	}
	public double getValue()
	{
		return value;
	}

	// setrs
	public void setLayer(int layer)
	{
		this.layer = layer;
	}
	public abstract void setValue(double value);
}
