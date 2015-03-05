import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Author {
	private String name;
	private Date birthday;
	private Set<Publication> pubs;

	public Author(String name) {
		this.name = name;
		this.pubs = new HashSet<Publication>();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
