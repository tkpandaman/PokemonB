package tests;

import java.util.List;
import java.util.Random;

@SuppressWarnings("serial")
public class TestRandom extends Random{
	
		private List<Integer> sequenceOfInts;
		private int currentIndex;
		
		public TestRandom(List<Integer> sequenceOfInts) {
			this.sequenceOfInts = sequenceOfInts;
			this.currentIndex = 0;
		}
		
		@Override
		public int nextInt(int i) {
			int result = this.sequenceOfInts.get(currentIndex);
			this.currentIndex++;
			return result;
		}
	
}
