package program;

public class BackpropInfo {
	

protected Double error;
protected Double biasError;
protected Double[] weightError;



	public BackpropInfo() {
		
	}



	public Double getError() {
		return error;
	}



	public void setError(Double error) {
		this.error = error;
	}



	public Double getBiasError() {
		return biasError;
	}



	public void setBiasError(Double biasError) {
		this.biasError = biasError;
	}



	public Double[] getWeightError() {
		return weightError;
	}



	public void setWeightError(Double[] weightError) {
		this.weightError = weightError;
	}
}
