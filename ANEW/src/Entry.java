
public class Entry {
	private double valenceMean;
	private double arousalMean;
	private String word;
	
	public Entry(String word, double valenceMean, double arousalMean) {
		this.word = word;
		this.valenceMean = valenceMean;
		this.arousalMean = arousalMean;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public double getValence() {
		return valenceMean;
	}
	
	public double getArousal() {
		return arousalMean;
	}
	
	public String toString() {
		return word + " " + valenceMean + " " + arousalMean;
	}
}
