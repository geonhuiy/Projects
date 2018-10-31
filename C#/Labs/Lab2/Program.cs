 using System;

namespace Lab2
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			Console.WriteLine ("Lab 2");
			Die die = new Die ();
			Console.WriteLine ("The number rolled: " + die.Roll ());
			Player p = new Player (die);
			Console.WriteLine ("Player 1 rolled: " + p.Throw ());
			for (int i = 1; i <= 10; i++) {
				Console.WriteLine (i + ".Player 1 rolled: " + p.Throw ());
			}
		}
	}
}
