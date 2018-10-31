using System;

namespace Lab5
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			double price = 100;
			for (int i = 0; i <= 12; i++) {
				price = Math.Round (price, 2);
				double percent = (1.5 / 100);
				Console.WriteLine ("The price is " + price);
				price = price + (price * percent);
			}
		}
	}
}
