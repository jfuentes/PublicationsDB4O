import java.util.ArrayList;
import java.util.List;

public class Publication {
	private String title;
	private int year;
	private List<Author> authors;

	public Publication(String title) {
		this.title = title;
		this.authors = new ArrayList<Author>();
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addAuthor(Author author) {
		authors.add(author);
		
	}

	public int getYear() {
		return year;
	}

}