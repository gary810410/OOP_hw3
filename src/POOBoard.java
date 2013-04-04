
public class POOBoard extends base_file{
	
	private String name;
	private static final int MAX_Article = 1024;
	private POOArticle[] articles;
	private int article_count;
	private POODirectory upper_level;
	
	public POOBoard(String name)
	{
		super(name);
		this.name = name;
		articles = new POOArticle[MAX_Article];
		article_count = 0;
	}
	
	public void add(POOArticle article)
	{
		articles[article_count] = article;
		articles[article_count].set_upper(this);
		article_count ++;
	}
	
	public void del(int pos)
	{
		if(pos < article_count)
		{
			articles[pos] = null;
			for(int i=pos; i<article_count-1; i++)
				articles[i] = articles[i+1];
			articles[article_count-1] = null;
			article_count --;
		}
	}
	
	public void move(int src, int dest)
	{
		if(src<article_count && dest<article_count)
		{
			POOArticle tmp = articles[src];
			articles[src] = articles[dest];
			articles[dest] = tmp;
		}
	}
	
	public int length()
	{
		return article_count;
	}
	
	public void show()
	{
		for(int i=0; i<article_count; i++)
			articles[i].list();
	}
	
	public String get_name()
	{
		return name;
	}
	
	public void set_upper(POODirectory upper)
	{
		upper_level = upper;
	}
	
	public POODirectory go_up()
	{
		return upper_level;
	}
	
	public POOArticle goto_next_level(int id)
	{
		POOArticle current = null;
		for(int i=0; i<article_count; i++)
		{
			current = articles[i];
			if(current.get_id() == id)
				break;
			current = null;
		}
		return current;
	}
	public void check(String name)
	{
		for(int i=0; i<article_count; i++)
		{
			if(articles[i].get_name().equals(name))
				articles[i].list();
		}
	}
	
	public void detail(int id)
	{
		for(int i=0; i<article_count; i++)
		{
			if(articles[i].get_id() == id)
				articles[i].show();
		}
	}
}
