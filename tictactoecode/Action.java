public class Action 
{
	private int x, y;
	public Action()
	{
		
	}
	public Action(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int[] getAction()
	{
		int[] action = {this.x, this.y};
		return (action);
	}
	public void setAction(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
