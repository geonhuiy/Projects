using System;

namespace Lab1
{
	public class Driver
	{
		private Car myCar;

		public Driver (Car myCar) 
		{
			this.myCar = myCar;
		}

		/*
		 * Sets 20 litres of gasoline to tank
		 * Accelerates as long as the speed is less than 50
		 * kilometers per hour and there is gasoline left in the tank
		 * After this, start decelerating the car
		 */


		public void Drive () {
			myCar.FillTank ();

			while ( (myCar.GetSpeed() < 50.0) && (myCar.GetGasLevel() >= 0.1) ) {
				myCar.Accelerate();
			}

			while ( myCar.GetSpeed() > 0 )  {
				myCar.Decelerate();
			}

		}
	}
}

