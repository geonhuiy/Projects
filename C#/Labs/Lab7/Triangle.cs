using System; 
namespace Lab7
{
	public class Triangle
	{
		private int size = 60;
		public Triangle ()
		{
			DrawStar (size);
		}
			private void DrawStar (int count) 
		{
				for (int i = 1; i <= count; i++) {
				for (int k = 1; k <= i; k++) {
					Console.Write ("*");
				}
				Console.WriteLine ();
			}
		}
	}
}

