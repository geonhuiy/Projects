using System;

namespace Lab4
{
	public class LoopTester{

			public void DoTheLoops (){
			int pc = 0, passengerLimit = 4;
			while(pc <= passengerLimit) {
				Console.WriteLine("Now I have" + pc + " passengers");
				pc = pc + 1;
			}   
			}
}
}

