using System;

namespace Lab1
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			Console.WriteLine ("Volvo is created!");
			Car volvo = new Car ();
			//Fill volvo's tank (set to 20 litres)
			volvo.FillTank();
			Console.WriteLine ("Speed = " + volvo.GetSpeed () + "km/h");
			Console.WriteLine ("Gas level = " + volvo.GetGasLevel() + "litres");

			Car bmw = new Car ();
			bmw.FillTank ();
			Console.WriteLine ("BMW is created!");
			Console.WriteLine ("Speed = " + bmw.GetSpeed () + "km/h");
			Console.WriteLine ("Gas level = " + volvo.GetGasLevel () + "litres");

			Driver me = new Driver (volvo);
			Driver she = new Driver (bmw);
		
			Console.WriteLine ("***** Me driving volvo");
			me.Drive ();
			Console.WriteLine ("***** She driving bmw");
			she.Drive ();

		}
	}
}
