
public class POOArticle extends base_file{
	
	private static int total_id = 0;
	private int id;
	private String title;
	private String author;
	private String content;
	private int evaluation_count;
	private static final int MAXEVAL = 100;
	private String[] evaluation_message;
	private int total_evaluation;
	private POOBoard upper_level;
	
	public POOArticle(String title, String author, String content)
	{
		super(title);
		id = total_id;
		total_id = (total_id+1)%1000;
		this.title = title;
		this.author = author;
		this.content = content;
		evaluation_message = new String[MAXEVAL];
		evaluation_count = 0;
		total_evaluation = 0;
	}
	
	public void add_message(int push_boo_arraw, String mesg)
	{
		// push_boo_arraw: 1:push; -1:boo; 0:arraw
		switch(push_boo_arraw)
		{
			case 1:
			case -1:
			case 0:
				evaluation_count += push_boo_arraw;
				break;
			default:
				return;
		}
		evaluation_message[total_evaluation] = mesg;
		total_evaluation ++;
	}
	
	public int push(String mesg)
	{
		if(total_evaluation <= MAXEVAL)
		{
			add_message(1,mesg);
			return 1;
		}
		else
			return 0;
	}
	
	public int boo(String mesg)
	{
		if(total_evaluation <= MAXEVAL)
		{
			add_message(-1,mesg);
			return 1;
		}
		else
			return 0;
	}
	
	public int arraw(String mesg)
	{
		if(total_evaluation <= MAXEVAL)
		{
			add_message(0,mesg);
			return 1;
		}
		else
			return 0;
	}
	
	public void show()
	{
		System.out.println("id: "+id+" title: "+title+" author: "+author);
		System.out.println("content: "+content);
		System.out.println("message#: "+total_evaluation+" evaluation: "+evaluation_count);
		for(int i=0; i<total_evaluation; i++)
			System.out.println(evaluation_message[i]);
	}
	
	public void list()
	{
		System.out.println("evaluation: "+evaluation_count+" id: "+id+" title: "+title+" author: "+author);
	}
	
	public String get_name()
	{
		return title;
	}
	
	public void set_upper(POOBoard upper)
	{
		upper_level = upper;
	}
	
	public POOBoard go_up()
	{
		return upper_level;
	}
	
	public int get_id()
	{
		return id;
	}
}
