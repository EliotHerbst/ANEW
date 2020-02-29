import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		
		File file = new File("D:\\Programs\\UnityFiles\\JSONReader\\Assets\\Resources\\AudioFiles\\ANEW\\src\\entries2.csv");
		Set<Entry> entries = new HashSet<Entry>();
		BufferedReader r = new BufferedReader(new FileReader(file));
		String line = "";
		try {
			r.readLine();
			while(true) {

				line = r.readLine();

				line = line.substring(line.indexOf(",") + 1);
				String name = line.substring(0, line.indexOf(","));
				line = line.substring(line.indexOf(",") + 1);

				double valence = Double.parseDouble(line.substring(0, line.indexOf(",")));
				line = line.substring(line.indexOf(",") + 1);
				line = line.substring(line.indexOf(",") + 1);
				line = line.substring(line.indexOf(",") + 1);

				double arousal = Double.parseDouble(line.substring(0, line.indexOf(",")));
				entries.add(new Entry(name, valence, arousal));

				
			}

		}
		catch (Exception e) {
			r.close();
		} 
		File wordList = new File("D:\\Programs\\UnityFiles\\JSONReader\\Assets\\Resources\\AudioFiles\\ANEW\\src\\2019Q1\\Other.txt");
		Map<Double, Integer> valence = new HashMap<Double, Integer>();
		Map<Double, Integer> arousal = new HashMap<Double, Integer>();
		r = new BufferedReader(new FileReader(wordList));
		String word = "";
		int numberCalm = 0;
		int numberSadness = 0;
		int numberAngry = 0;
		int numberHappy = 0;
		int total = 0;
		try {
			while(true) {
				word = r.readLine();
				int count = Integer.parseInt(word.substring(word.indexOf('	') +1));
				word = word.substring(0, word.indexOf('	'));
				for(Entry e : entries) {
					if(e.getWord().equals(word)) {
						total++;
						System.out.print(word+","+e.getValence()+","+e.getArousal()+","+count);
						if(valence.containsKey(e.getValence())) {
							valence.put(e.getValence(), count + valence.get(e.getValence()));
						}
						else {
							valence.put(e.getValence(), count);
						}
						if(arousal.containsKey(e.getArousal())) {
							arousal.put(e.getArousal(), count + arousal.get(e.getArousal()));
						}
						else {
							arousal.put(e.getArousal(), count);
						}
						if(e.getValence() < 5 && e.getArousal() >= 5) {
							System.out.println(",angry");
							numberAngry += count;
						}
						else if(e.getValence() >= 5 && e.getArousal() >= 5) {
							System.out.println(",happy");
							numberHappy += count;
						}
						else if(e.getValence() < 5 && e.getArousal() < 5) {
							System.out.println(",sadness");
							numberSadness += count;
						}
						else {
							System.out.println(",calm");
							numberCalm += count;
						}
					}
				}
				
			}

		}
		catch (Exception e) {
			r.close();
		} 
		System.out.println(numberAngry + "," + numberHappy + "," + numberSadness + "," + numberCalm);
		
		double uWValence = 0;
		double uWArousal = 0;
		int totalV = 0;
		
		double wValence = 0;
		double wArousal = 0;
		int totalA = 0;
		
		for(Double d : valence.keySet()) {
			uWValence += d;
			double count = valence.get(d);
			totalV += count;
		}
		uWValence /= total;
		for(Double d : valence.keySet()) {
			double count = valence.get(d);
			wValence += d * (count / totalV);
		}
		for(Double d : arousal.keySet()) {
			uWArousal += d;
			double count = arousal.get(d);
			totalA += count;
		}
		uWArousal /= total;
		for(Double d : arousal.keySet()) {
			double count = arousal.get(d);
			wArousal += d * (count / totalA);
		}
		System.out.println(uWValence + "," + uWArousal + "," + wValence +"," + wArousal);
		
	}

}
