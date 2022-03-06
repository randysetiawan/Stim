
public class Game {
	private String gameId, name;
	private int price;
	private String genreId;
	private int quantity;

	public Game(String gameId, String name, int price, String genreId, int quantity) {
		this.gameId = gameId;
		this.name = name;
		this.price = price;
		this.genreId = genreId;
		this.quantity = quantity;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
