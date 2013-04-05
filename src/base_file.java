
public class base_file {
	private String name;
	private int length;
	private base_file upper_level;
	
	public base_file(String name)
	{
		this.name = name;
		length = 0;
		upper_level = null;
	}
	public base_file()
	{
		name = "--------------------";
	}
	
	public void where()
	{
		if(upper_level != null)
			this.go_up().where();
		if(upper_level == null)
			System.out.print(name);
		else
			System.out.print("->"+name);
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
	
	public void set_upper(base_file upper)
	{
		upper_level = upper;
	}
	
	public base_file go_up()
	{
		return upper_level;
	}
}
