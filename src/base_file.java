
public class base_file {
	private String name;
	private int length;
	
	public base_file(String name)
	{
		this.name = name;
		length = 0;
	}
	public base_file()
	{
		name = "--------------------";
	}
	
	public void show_name()
	{
		System.out.println(name);
	}
	
	public String get_name()
	{
		return name;
	}
	
	public int length()
	{
		return length;
	}
}
