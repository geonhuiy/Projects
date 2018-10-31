using System;

namespace Lab3
{
	public class Player
	{
		private Roulette myRoulette;
		public Player (Roulette myRoulette)
		{
			this.myRoulette = myRoulette;
		}
		public int play() {
			return myRoulette.Spin();
		}
	}
}

