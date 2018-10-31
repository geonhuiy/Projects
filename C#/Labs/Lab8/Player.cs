using System;
using Tools; /*calling from a different namespace*/
using System.Collections.Generic;

namespace Humans
{
	public class Player
	{
		private Die myDie;
		public Player (Die nakedMan)
		{
			this.myDie = nakedMan;
		}
		/// <summary>
		/// Rolls a die until all numbers exist at least once.
		/// </summary>
		/// <returns>The number of rolls needed.</returns>
		public int RollAllNumbers() {
			//Creat a list where an element knows if a certain number is thrown already

			//Define a list for die numbers thrown
			int counter = 0;
			List <int> numbersThrown = new List <int>();
			do {
			//Throw the die
				int nbr = this.myDie.Roll();
				counter++;
			//Thrown number not in the list?
				if (!numbersThrown.Contains(nbr)) {
			//Add the number to the list
					numbersThrown.Add(nbr);
				}
			//Throw again if all 6 numbers are not on the list
			}while(numbersThrown.Count<6);
			return counter;
		}
	}
}

