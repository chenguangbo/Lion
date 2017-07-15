package co.baidu.po;

public class Book {

	public Book() {
	}


	
	public Book(int id, String name, String price, String pic, String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.description = description;
	}



	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", pic=" + pic + ", description="
				+ description + "]";
	}

	private int id;

	private String name;

	private String price;

	private String pic;

	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
