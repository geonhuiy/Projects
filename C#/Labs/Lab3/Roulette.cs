using System;

namespace Lab3
{
	public class Roulette
	{
		private Random random;

		public Roulette ()
		{
			this.random = new Random ();
		}
		public int Spin() {
			int number = this.random.Next(0,37);
			//Doesnt move on if 0 is selected
			if (number !=0) {
				number = this.random.Next (0, 37);
			}
			return number;
		}  
	}
}

