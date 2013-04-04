
public class POODirectory extends base_file{
	
	private String name;
	private static final int MAX_Item = 1024;
	private int item_count;
	private base_file[] files;
	
	public POODirectory(String name)
	{
		super(name);
		this.name = name;
		item_count = 0;
		files = new base_file[MAX_Item];
	}
	
	public int add(POOBoard board)
	{
		if(item_count >= MAX_Item)
			return 0;
		else
		{
			files[item_count] = board;
			item_count ++;
			return 1;
		}
	}
	
	public int add(POODirectory dir)
	{
		if(item_count >= MAX_Item)
			return 0;
		else
		{
			files[item_count] = dir;
			item_count ++;
			return 1;
		}
	}
	
	public int add_split()
	{
		if(item_count >= MAX_Item)
			return 0;
		else
		{
			files[item_count] = new base_file();
			item_count ++;
			return 1;
		}
	}
	
	public int del(int pos)
	{
		if(pos >= item_count)
			return 0;
		else
		{
			files[pos] = null;
			for(int i=pos; i<item_count-1; i++)
				files[i] = files[i+1];
			files[item_count-1] = null;
			item_count --;
			return 1;
		}
	}
	
	public int move(int src, int dest)
	{
		if(src >= item_count || dest>= item_count)
			return 0;
		else
		{
			base_file tmp = files[src];
			files[src] = files[dest];
			files[dest] = tmp;
			return 1;
		}
	}
	
	public int length()
	{
		return item_count;
	}
	
	public void show()
	{
		for(int i=0; i<item_count; i++)
			files[i].show_name();
	}
	
	public String get_name()
	{
		return name;
	}
}
