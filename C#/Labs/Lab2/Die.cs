using System;

namespace Lab2
{
	public class Die
	{
		private Random random;
		public Die ()

		{
			// Random generator has to be created only once first.
			// Gets the seed from the system clock.
			this.random = new Random();
		}

		//Method Roll() returns random integer from 1 to 6
		public int Roll () {
			return this.random.Next (1, 7);
		}
	}
}

