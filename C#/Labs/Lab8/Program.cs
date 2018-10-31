 using System;
using Tools;
using Humans;

namespace Lab8
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			Die noppa = new Die ();
			Player Alex = new Player (noppa);
			Console.WriteLine ("Number of rolls needed " + Alex.RollAllNumbers());
		}
	}
}
