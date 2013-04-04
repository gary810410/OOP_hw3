
public class base_file {
	private String name;
	
	public base_file(String name)
	{
		this.name = name;
	}
	public base_file()
	{
		name = "--------------------";
	}
	
	public void show_name()
	{
		System.out.println(name);
	}
}
