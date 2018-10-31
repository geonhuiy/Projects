using System;

namespace Lab2
{
	public class Player
	{
		private Die myDie;

		public Player (Die myDie)
		{
			this.myDie = myDie;
		}
		public int Throw() {
			return myDie.Roll();
		}
	}
}

