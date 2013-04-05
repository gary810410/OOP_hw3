
import java.util.Scanner;
import java.io.*;

public class Demo {
	
	public static base_file current;
	public static final POODirectory root = new POODirectory("root");
	public static final String[][] directions =
	{	
		{"where","where:\n\tshow your location"},
		{"list","list:\n\tlist the directories/boards/articles this board/article has"},
		{"goto","goto dir_name/board_name/article_id:\n\tgoto directory/board/articles"},
		{"up","up:\n\tgo to upper directory/board"},
		{"check","check article_name:\n\tlist the articles in the board with the same name"},
		{"detail","detail id_number:\n\tshow the detail information of the article"},
		{"addboard","addboard board_name:\n\tadd a new board to current directory"},
		{"adddir","adddir diry_name:\n\tadd a new directory to current directory"},
		{"addarticle","addarticle title author content:\n\tadd a new article to current board"},
		{"push","push message:\n\tpush the article"},
		{"boo","boo message:\n\tboo the article"},
		{"arrow","arrow message:\n\tarrow the article"},
		{"exit","exit:\n\texit this program"},
		{"help","help:\n\thelp you"}
	};
	
	
	public static void main(String argv[])
	{
		Scanner input = new Scanner(System.in);
		String command;
		current = root;
		boolean leave;
		
		System.out.println("you are in root directory");
		System.out.println("type some command or use help to get more information");
		if(argv.length >= 1)
		{
			File file = new File(argv[0]);
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String text = null;

				// repeat until all lines is read
				while ((text = reader.readLine()) != null)
				{
					String[] split_command = text.split("[ ]+");
					leave = execute(split_command);
					if(leave == false)
					{
						reader.close();
						return;
					}
				}
				reader.close();
	        }catch(IOException e){
	        	;
	        }
		}
		while(true)
		{
			command = input.nextLine();
			String[] split_command = command.split("[ ]+");
			leave = execute(split_command);
			if(leave == false)
				break;
		}
		input.close();
	}
	public static boolean execute(String[] split_command)
	{
		base_file next;
		int id;
		switch(split_command[0])
		{
			case "where":	// where: show the current location
				System.out.print("you are at:");
				current.where();
				System.out.println("");
				break;
			case "list":	// list: show the items in the current directory/board
				System.out.println(current.get_name()+" has:");
				if(current instanceof POODirectory)
					((POODirectory) current).show();
				else if(current instanceof POOBoard)
					((POOBoard) current).show();
				else if(current instanceof POOArticle)
					((POOArticle) current).show();
				break;
			case "goto":	// goto directory_name/board_name/article_id: go to directory/board/article
				if(split_command.length <=1)
					break;
				if(current instanceof POODirectory)
				{
					next = ((POODirectory) current).goto_next_level(split_command[1]);
					if(next != null)
						current = next;
				}
				else if(current instanceof POOBoard)
				{
					try{
						id = Integer.valueOf(split_command[1]);
					}catch(NumberFormatException err)
					{
						break;
					}
					next = ((POOBoard) current).goto_next_level(id);
					if(next != null)
						current = next;
				}
				System.out.print("you are at:");
				current.where();
				System.out.println("");
				break;
			case "up":		// up: go to the upper directory
				if(current instanceof POODirectory)
				{
					next = ((POODirectory) current).go_up();
					if(next != null)
						current = next;
				}
				else if(current instanceof POOBoard)
				{
					next = ((POOBoard) current).go_up();
					if(next != null)
						current = next;
				}
				else if(current instanceof POOArticle)
				{
					next = ((POOArticle) current).go_up();
					if(next != null)
						current = next;
				}
				System.out.print("you are at:");
				current.where();
				System.out.println("");
				break;
			case "check":	// check article_name: list the articles in the board with the same name
				if(split_command.length <=1)
					break;
				if(current instanceof POOBoard)
					((POOBoard) current).check(split_command[1]);
				break;
			case "detail":	// detail id_number: show the detail information of the article
				if(split_command.length <=1)
					break;
				try{
					id = Integer.valueOf(split_command[1]);
				}catch(NumberFormatException err)
				{
					break;
				}
				if(current instanceof POOBoard)
					((POOBoard) current).detail(id);
				break;
			case "addboard":	// addboard board_name: add a new board to current directory
				if(split_command.length <=1)
					break;
				POOBoard new_board;
				if(current instanceof POODirectory)
				{
					 new_board = new POOBoard(split_command[1]);
					 new_board.set_upper(((POODirectory) current));
					 ((POODirectory) current).add(new_board);
				}
				break;
			case "adddir":		// adddir directory_name: add a new directory to current directory
				if(split_command.length <=1)
					break;
				POODirectory new_dir;
				if(current instanceof POODirectory)
				{
					 new_dir = new POODirectory(split_command[1]);
					 new_dir.set_upper(((POODirectory) current));
					 ((POODirectory) current).add(new_dir);
				}
				break;
			case "addarticle":	// addarticle title author content: add a new article to current board
				if(split_command.length <=4)
					break;
				String title = split_command[1];
				String author = split_command[2];
				String content = split_command[3];
				POOArticle new_article;
				if(current instanceof POOBoard)
				{
					new_article = new POOArticle(title, author, content); 
					((POOBoard) current).add(new_article);
				}
				break;
			case "push":	// push message: push the article
				if(split_command.length <=1)
					break;
				if(current instanceof POOArticle)
					 ((POOArticle) current).push(split_command[1]);
				break;
			case "boo":		// boo message: boo the article
				if(split_command.length <=1)
					break;
				if(current instanceof POOArticle)
					 ((POOArticle) current).boo(split_command[1]);
				break;
			case "arrow":	// arrow message: arrow the article
				if(split_command.length <=1)
					break;
				if(current instanceof POOArticle)
					 ((POOArticle) current).arrow(split_command[1]);
				break;
			case "exit":	// exit: exit
				return false;
			case "help":	// help: help
				if(split_command.length == 1)
					for(int i=0; i<directions.length;i++)
						System.out.println(directions[i][1]);
				else
					for(int i=0; i<directions.length;i++)
					{	
						String regx = ".*"+split_command[1]+".*";
						if(directions[i][0].matches(regx))
							System.out.println(directions[i][1]);
					}
				break;
			default:
				;
		}
		return true;
	}
	
}
