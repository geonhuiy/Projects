using System;

namespace Tools
{
	public class Die
	{
		Random rnd;
		public Die ()
		{
			this.rnd = new Random ();
		}
		public int Roll() {
			int number = rnd.Next (1, 7);
			return number;
			//Method returns something, constructor doesnt return anything
		}
	}
}

