using System;

namespace Lab3
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			Console.WriteLine ("Lab 3");
			Roulette r = new Roulette ();
			Player p1 = new Player (r);
			Console.WriteLine ("Player 1 rolled: " + p1.play ());

		}
	}
}
