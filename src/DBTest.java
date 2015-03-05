import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

public class DBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create a publication
		Publication article = new Publication("Concepts for Content Management");
		// create authors
		Author michael = new Author("Michael Grossniklaus");
		Author moira = new Author("Moira C . Norrie");
		// assign authors to publication
		article.addAuthor(michael);
		article.addAuthor(moira);
		// store complex object
		ObjectContainer db = Db4oEmbedded.openFile("test.db");
		db.store(article);
		

		/*
		 * Query By Example
		 */
		// ObjectContainer db = Db4oEmbedded . openFile ( "test.db" );
		// get author " Moira C . Norrie "
		Author proto = new Author("Moira C . Norrie");
		ObjectSet<Author> authors = db.queryByExample(proto);
		for (Author author : authors) {
			System.out.println(author.getName());
		}
		// get all publications
		ObjectSet<Publication> publications = db.query(Publication.class);
		for (Publication publication : publications) {
			System.out.println(publication.getTitle());
		}

		/*
		 * Native Queries
		 */
		// ObjectContainer db = Db4oEmbedded . openFile ( "test.db" );
		// find all publications after 1995
		ObjectSet<Publication> publications2 = db
				.query(new Predicate<Publication>() {
					public boolean match(Publication publication) {
						return publication.getYear() > 1995;
					}
				});
		for (Publication publication : publications2) {
			System.out.println(publication.getTitle());
		}

		/*
		 * SODA Queries
		 */
		// Encontrar todas las publicaciones despues del 1995
		Query query = db.query();
		query.constrain(Publication.class);
		query.descend("year").constrain(Integer.valueOf(1995)).greater();
		ObjectSet<Publication> publications3 = query.execute();
		for (Publication publication : publications3) {
			System.out.println(publication.getTitle());
		}
		// Encontrar todas las publicaciones del autor Moira C . Norrie
		Query query2 = db.query();
		query2.constrain(Publication.class);
		Author proto2 = new Author("Moira C . Norrie");
		query.descend("authors").constrain(proto2).contains();
		ObjectSet<Publication> publications4 = query2.execute();
		for (Publication publication : publications4) {
			System.out.println(publication.getTitle());
		}
		db.commit();
		
		db.close();

	}

}
