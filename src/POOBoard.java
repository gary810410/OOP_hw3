
public class POOBoard extends base_file{
	
	private String name;
	private static final int MAX_Article = 1024;
	private POOArticle[] articles;
	private int article_count;
	
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
			articles[i].show_name();
	}
	
	public String get_name()
	{
		return name;
	}
}
